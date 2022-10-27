package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.SearchStatistics;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SearchStatisticsRepository extends CrudRepository<SearchStatistics, Long> {

    Optional<SearchStatistics> findSearchStatisticsBySearchedTitle(String searchTitle);

}
