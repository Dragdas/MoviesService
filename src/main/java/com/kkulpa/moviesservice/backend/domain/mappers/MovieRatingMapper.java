package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieRatingDto;
import com.kkulpa.moviesservice.backend.domain.MovieRating;

public class MovieRatingMapper {

    public static MovieRatingDto mapToDto(MovieRating movieRating){

        return new MovieRatingDto(
                movieRating.getId(),
                movieRating.getReviewer().getId(),
                movieRating.getMovieDetails().getId(),
                movieRating.getRating(),
                movieRating.isFavourite()
        );

    }

}
