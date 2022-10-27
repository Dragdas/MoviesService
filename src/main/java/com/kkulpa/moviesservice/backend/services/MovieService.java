package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.client.OmdbClient;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.MovieDetailsRepository;
import com.kkulpa.moviesservice.backend.repositories.MovieRatingRepository;
import com.kkulpa.moviesservice.backend.repositories.UserRepository;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final OmdbClient omdbClient;
    private final MovieRatingRepository movieRatingRepository;
    private final UserService userService;
    private  final MovieDetailsService movieDetailsService;
    private final MovieDetailsRepository movieDetailsRepository;
    private final UserRepository userRepository;

    public List<MovieDto> getSearchResults(String title){
        return omdbClient.searchForMovies(title);
    }

    public MovieDetailsDto getMovieDetails(String imdbId) throws MovieDetailsUnavailableException {
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
