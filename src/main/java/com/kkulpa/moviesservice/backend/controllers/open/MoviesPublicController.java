package com.kkulpa.moviesservice.backend.controllers.open;


import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
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

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMoviesSuggestions(){
        return ResponseEntity.ok(List.of(new MovieDto("stub title","2022", "stub id")));
    }

}
