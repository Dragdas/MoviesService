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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void changeFavouriteStatus(ApplicationUser requestingUser, String imdbId) throws Exception {


        User user = userService.getSessionUser(requestingUser);

        Optional<MovieDetails> movieDetailsOptional = movieDetailsService.getMovieDetailsByImdbId(imdbId);
        MovieDetails movieDetails = movieDetailsOptional.isPresent() ?
                                                        movieDetailsOptional.get()
                                                        : movieDetailsService.addNewMovieDetailsCard(imdbId);

        MovieRating movieRatingToBeSaved = movieRatingRepository
                .findMovieRatingByUserNameAndIMDB(user.getUserName(), imdbId)
                .orElse(new MovieRating(
                        null,
                        user,
                        movieDetails,
                        0,
                        false
                        ));

        movieRatingToBeSaved.setFavourite( !movieRatingToBeSaved.isFavourite() );


        movieDetailsRepository.save(movieDetails);
        movieRatingToBeSaved = movieRatingRepository.save(movieRatingToBeSaved);

        user.getRatings().add(movieRatingToBeSaved);
        userRepository.save(user);
    }



}
