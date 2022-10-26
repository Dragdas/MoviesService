package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieDetailsRepository extends CrudRepository<MovieDetails, Long> {

    Optional<MovieDetails> findMovieDetailsByImdbID(String imdbId);

    boolean existsByImdbID(String imdbId);

}
