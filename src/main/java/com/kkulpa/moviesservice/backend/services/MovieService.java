package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.client.OmdbClient;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import com.kkulpa.moviesservice.backend.repositories.MovieRatingRepository;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final OmdbClient omdbClient;
    private final MovieRatingRepository movieRatingRepository;
    private final UserService userService;

    public List<MovieDto> getSearchResults(String title){
        return omdbClient.searchForMovies(title);
    }

    public MovieDetailsDto getMovieDetails(String imdbId) throws MovieDetailsUnavailableException {
        return omdbClient.getMovieDetails(imdbId);
    }

    public void changeFavouriteStatus(ApplicationUser requestingUser, String imdbId){

        //TODO wykorzystac serwis po zmianach
        //User user = userService.getSessionUser()

/*        MovieRating movieRatingToBeSaved = movieRatingRepository
                .findMovieRatingByUserNameAndIMDB(requestingUser.getUsername(), imdbId)
                .orElse(new MovieRating(
                        null,

                        ));*/


    }



}
