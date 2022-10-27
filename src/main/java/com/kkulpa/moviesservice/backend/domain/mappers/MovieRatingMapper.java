package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieRatingDto;
import com.kkulpa.moviesservice.backend.domain.MovieRating;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<MovieRatingDto> mapListToDtos(List<MovieRating> movieRatings){
        return movieRatings.stream()
                .map(MovieRatingMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
