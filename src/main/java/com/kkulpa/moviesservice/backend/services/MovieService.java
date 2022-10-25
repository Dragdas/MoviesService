package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.client.OmdbClient;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final OmdbClient omdbClient;

    public List<MovieDto> getSearchResults(String title){
        return omdbClient.searchForMovies(title);
    }

    public MovieDetailsDto getMovieDetails(String imdbId) throws MovieDetailsUnavailableException {
        return omdbClient.getMovieDetails(imdbId);
    }



}
