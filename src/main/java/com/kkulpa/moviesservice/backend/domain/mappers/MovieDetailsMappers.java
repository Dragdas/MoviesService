package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDetailsMappers {

    public static MovieDetailsDto mapToDto(MovieDetails movieDetails){

        return new MovieDetailsDto(
                movieDetails.getImdbID(),
                movieDetails.getTitle(),
                movieDetails.getGenre(),
                movieDetails.getDirector(),
                movieDetails.getPlot(),
                movieDetails.getPoster(),
                movieDetails.getBoxOffice()
        );
    }

    public static List<MovieDetailsDto> mapListToDto(List<MovieDetails> movieDetails){
        return movieDetails.stream()
                .map(MovieDetailsMappers::mapToDto)
                .collect(Collectors.toList());

    }

}
