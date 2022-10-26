package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.client.OmdbClient;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import com.kkulpa.moviesservice.backend.domain.MovieStatistics;
import com.kkulpa.moviesservice.backend.repositories.MovieDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieDetailsService {

    private final MovieDetailsRepository movieDetailsRepository;
    private final OmdbClient omdbClient;

    public Optional<MovieDetails> getMovieDetailsByImdbId(String imdbId){
        return movieDetailsRepository.findMovieDetailsByImdbID(imdbId);
    }

    public MovieDetails addNewMovieDetailsCard(String imdbId) throws Exception {
        if(movieDetailsRepository.existsByImdbID(imdbId))
            throw new Exception("MovieDetails card already created");

        MovieDetailsDto movieDetails = omdbClient.getMovieDetails(imdbId);
        return movieDetailsRepository.save(new MovieDetails(
                null,
                movieDetails.getImdbID(),
                movieDetails.getTitle(),
                movieDetails.getGenre(),
                movieDetails.getDirector(),
                movieDetails.getPlot(),
                movieDetails.getPoster(),
                movieDetails.getBoxOffice(),
                new MovieStatistics(null, movieDetails.getImdbID(), 1),
                new ArrayList<>(),
                new ArrayList<>()
        ));
    }




}
