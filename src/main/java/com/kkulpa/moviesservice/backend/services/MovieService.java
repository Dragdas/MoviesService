package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.client.OmdbClient;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieCommentDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import com.kkulpa.moviesservice.backend.domain.MovieComment;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.domain.mappers.MovieDetailsMappers;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.AccessDeniedException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.CommentNotFoundException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.MovieCommentRepository;
import com.kkulpa.moviesservice.backend.repositories.MovieDetailsRepository;
import com.kkulpa.moviesservice.backend.repositories.MovieRatingRepository;
import com.kkulpa.moviesservice.backend.repositories.UserRepository;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final OmdbClient omdbClient;
    private final UserService userService;
    private  final MovieDetailsService movieDetailsService;
    private final MovieRatingRepository movieRatingRepository;
    private final MovieDetailsRepository movieDetailsRepository;
    private final UserRepository userRepository;
    private final MovieCommentRepository movieCommentRepository;




    public List<MovieDto> getSearchResults(String title){

        return omdbClient.searchForMovies(title);
    }

    public MovieDetailsDto getMovieDetails(String imdbId) throws MovieDetailsUnavailableException {

        Optional<MovieDetails> movieDetailsInRepo = movieDetailsRepository.findMovieDetailsByImdbID(imdbId);

        if(movieDetailsInRepo.isPresent())
            return MovieDetailsMappers.mapToDto(movieDetailsInRepo.get());

        return omdbClient.getMovieDetails(imdbId);
    }

    public List<MovieDetails> getUsersFavouriteMovies(ApplicationUser requestingUser ) throws UserNotFoundException {

        User user = userService.getSessionUser(requestingUser);

        return user.getRatings().stream()
                .filter(MovieRating::isFavourite)
                .map(MovieRating::getMovieDetails)
                .collect(Collectors.toList());
    }

    public List<MovieDetails> getUsersRatedMovies(ApplicationUser requestingUser ) throws UserNotFoundException{

        User user = userService.getSessionUser(requestingUser);

        return user.getRatings().stream()
                .filter( rating -> rating.getRating() > 0)
                .map(MovieRating::getMovieDetails)
                .collect(Collectors.toList());

    }

    public List<MovieDetails> getMovieRankingByFavouritesCount(){

        return movieRatingRepository.findMostFavouriteMovies();

    }

    public Map<MovieDetailsDto, Double>  getMovieRankingByRating(){

        Map<MovieDetailsDto, Double> ranking = new HashMap<>();

        List<MovieRating> ratedMovies = movieRatingRepository.findRatedMovies();

        Set<String> imdbIDs = ratedMovies.stream()
                .map(MovieRating::getMovieDetails)
                .map(MovieDetails::getImdbID)
                .collect(Collectors.toSet());

        imdbIDs.stream().forEach(imdbID ->{
            Optional<MovieDetails> movieDetails = ratedMovies.stream()
                    .map(MovieRating::getMovieDetails)
                    .filter(details -> details.getImdbID().equals(imdbID))
                    .findFirst();

            OptionalDouble averageScore = ratedMovies.stream()
                    .filter(movieRating -> movieRating.getMovieDetails().getImdbID().equals(imdbID))
                    .mapToInt(MovieRating::getRating)
                    .average();

            if(movieDetails.isPresent() && averageScore.isPresent())
                ranking.put( MovieDetailsMappers.mapToDto(movieDetails.get()),
                             averageScore.getAsDouble());
        });

        return ranking;
    }

    @Transactional
    public List<MovieComment> addComment(ApplicationUser requestingUser, String imdbId, String comment)
            throws Exception {

        User user = userService.getSessionUser(requestingUser);

        MovieDetails movieDetails = movieDetailsService.getOrAddMovieDetails(imdbId);

        movieDetails.getComments().add(new MovieComment(
                                            null,
                                            user,
                                            movieDetails,
                                            comment
                                            ));

        return movieDetailsRepository.save(movieDetails).getComments();
    }

    @Transactional
    public MovieComment updateComment(ApplicationUser requestingUser, Long commentId, String newComment)
                                                                            throws UserNotFoundException,
                                                                            AccessDeniedException,
                                                                            CommentNotFoundException {

        User user = userService.getSessionUser(requestingUser);

        MovieComment movieComment = movieCommentRepository.findById(commentId)
                                    .orElseThrow(CommentNotFoundException::new);

        if(!user.getUserName().equals(movieComment.getAuthor().getUserName()))
            throw new AccessDeniedException();

        movieComment.setComment(newComment);

        return movieCommentRepository.save(movieComment);
    }

    @Transactional
    public void deleteComment(ApplicationUser requestingUser, Long commentId)
                                                        throws UserNotFoundException,
                                                        CommentNotFoundException,
                                                        AccessDeniedException {

        User user = userService.getSessionUser(requestingUser);

        MovieComment movieComment = movieCommentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!user.getUserName().equals(movieComment.getAuthor().getUserName()))
            throw new AccessDeniedException();

        MovieDetails movieDetails = movieComment.getMovieDetails();

        movieDetails.getComments().remove(movieComment);

        movieDetailsRepository.save(movieDetails);
    }

    public List<MovieComment> getMovieCommentsByMovie(String imdbId){

        return movieCommentRepository.findMovieCommentByMovieDetails_ImdbID(imdbId);
    }

    public List<MovieComment> getMovieCommentsByAuthor(String username){

        return movieCommentRepository.findMovieCommentByAuthor_UserName(username);

    }





    @Transactional
    public void changeFavouriteStatus(ApplicationUser requestingUser, String imdbId) throws Exception {

        User user = userService.getSessionUser(requestingUser);
        MovieDetails movieDetails = getOrCreateMovieDetailsCard(imdbId);
        MovieRating movieRatingToBeSaved = getOrCreateMovieRating(imdbId, user, movieDetails);

        movieRatingToBeSaved.setFavourite( !movieRatingToBeSaved.isFavourite() );

        movieDetailsRepository.save(movieDetails);
        movieRatingToBeSaved = movieRatingRepository.save(movieRatingToBeSaved);
        user.getRatings().add(movieRatingToBeSaved);
        userRepository.save(user);
    }

    @Transactional
    public void changeMovieRating(ApplicationUser requestingUser, String imdbId, int score) throws Exception {

        User user = userService.getSessionUser(requestingUser);
        MovieDetails movieDetails = getOrCreateMovieDetailsCard(imdbId);
        MovieRating movieRatingToBeSaved = getOrCreateMovieRating(imdbId, user, movieDetails);


        movieRatingToBeSaved.setRating(normalizeScore(score));

        movieDetailsRepository.save(movieDetails);
        movieRatingToBeSaved = movieRatingRepository.save(movieRatingToBeSaved);
        user.getRatings().add(movieRatingToBeSaved);
        userRepository.save(user);
    }






    private int normalizeScore(int score){
        if(score<1)
            return 1;
        if(score>10)
            return 10;
        else return score;
    }

    private MovieDetails getOrCreateMovieDetailsCard(String imdbId) throws Exception {
        Optional<MovieDetails> movieDetailsOptional = movieDetailsService.getMovieDetailsByImdbId(imdbId);
        return movieDetailsOptional.isPresent() ?
                movieDetailsOptional.get()
                : movieDetailsService.addNewMovieDetailsCard(imdbId);
    }

    private MovieRating getOrCreateMovieRating(String imdbId, User user, MovieDetails movieDetails) {
        return movieRatingRepository
                .findMovieRatingByUserNameAndIMDB(user.getUserName(), imdbId)
                .orElse(new MovieRating(
                        null,
                        user,
                        movieDetails,
                        0,
                        false
                ));
    }


}
