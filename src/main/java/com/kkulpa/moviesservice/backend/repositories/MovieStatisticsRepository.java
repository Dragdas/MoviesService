package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.MovieStatistics;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieStatisticsRepository extends CrudRepository<MovieStatistics, Long> {

    Optional<MovieStatistics> findMovieStatisticsByImdbID(String imdbID);

}
