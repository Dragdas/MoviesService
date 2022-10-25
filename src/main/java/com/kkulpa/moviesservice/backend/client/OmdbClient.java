package com.kkulpa.moviesservice.backend.client;


import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.SearchDto;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OmdbClient {

    private final RestTemplate restTemplate;
    private final String API_ENDPOINT = "http://www.omdbapi.com/";
    private final String API_KEY = "8d1d6828"; // left here because it is example project

    public List<MovieDto> searchForMovies(String title) {

        URI url = generateSearchFilmRequestPath(title);

        SearchDto searchResponse = restTemplate.getForObject(url, SearchDto.class);

        if(searchResponse==null)
            return Collections.emptyList();
        return searchResponse.getFilms();
    }

    public MovieDetailsDto getMovieDetails(String imdbId) throws MovieDetailsUnavailableException {

        URI url = generateGetFilmDetailsRequestPath(imdbId);

        MovieDetailsDto filmDetailsResponse = restTemplate.getForObject(url, MovieDetailsDto.class);

        if(filmDetailsResponse==null)
            throw new MovieDetailsUnavailableException();
        return filmDetailsResponse;
    }

    private URI generateGetFilmDetailsRequestPath(String imdbId) {

        return UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .queryParam("apikey", API_KEY)
                .queryParam("i",imdbId)
                .build()
                .encode()
                .toUri();
    }

    private URI generateSearchFilmRequestPath(String title){

        return UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .queryParam("apikey", API_KEY)
                .queryParam("type", "movie")
                .queryParam("s", title)
                .build()
                .encode()
                .toUri();
    }
}
