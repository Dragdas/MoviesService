package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.SearchStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SearchStatisticsRepository extends CrudRepository<SearchStatistics, Long> {

    Optional<SearchStatistics> findSearchStatisticsBySearchedTitle(String searchTitle);

    @Query(value = "SELECT stat FROM SearchStatistics stat ORDER BY stat.searchCount DESC")
    List<SearchStatistics> getTopSearches();


}
