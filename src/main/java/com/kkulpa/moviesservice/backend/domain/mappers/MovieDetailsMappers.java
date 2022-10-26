package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;

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

}
