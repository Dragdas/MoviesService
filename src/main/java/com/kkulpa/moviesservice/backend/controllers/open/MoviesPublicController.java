package com.kkulpa.moviesservice.backend.controllers.open;


import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import com.kkulpa.moviesservice.backend.domain.mappers.MovieDetailsMappers;
import com.kkulpa.moviesservice.backend.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/movies")
@RequiredArgsConstructor
public class MoviesPublicController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMoviesSuggestions(){
        return ResponseEntity.ok(List.of(new MovieDto("stub title","2022", "stub id")));
    }

    @GetMapping(value = "/movieRecommendations")
    public ResponseEntity<List<MovieDetailsDto>> getMovieRecommendations(){

        List<MovieDetails> movies = movieService.getMovieRecommendations();

        return ResponseEntity.ok(MovieDetailsMappers.mapListToDto(movies));

    }

}
