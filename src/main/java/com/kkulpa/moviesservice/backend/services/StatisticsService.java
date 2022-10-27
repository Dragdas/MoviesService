package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.domain.MovieStatistics;
import com.kkulpa.moviesservice.backend.domain.SearchStatistics;
import com.kkulpa.moviesservice.backend.repositories.MovieStatisticsRepository;
import com.kkulpa.moviesservice.backend.repositories.SearchStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final SearchStatisticsRepository searchStatisticsRepository;
    private final MovieStatisticsRepository movieStatisticsRepository;

    @Transactional
    public void addSearchEvent(String searchTitle){

        Optional<SearchStatistics> searchStatOpt = searchStatisticsRepository
                .findSearchStatisticsBySearchedTitle(searchTitle);

        if(searchStatOpt.isPresent()){
            SearchStatistics searchStatistics = searchStatOpt.get();
            searchStatistics.setSearchCount(searchStatistics.getSearchCount()+1);
            searchStatisticsRepository.save(searchStatistics);
            return;
        }

        SearchStatistics newSearchStat = new SearchStatistics(null, searchTitle, 1);
        searchStatisticsRepository.save(newSearchStat);
    }

    @Transactional
    public void addMovieDetailImpression(String imdb){

        Optional<MovieStatistics> movieStatisticsOptional = movieStatisticsRepository.findMovieStatisticsByImdbID(imdb);

        if(movieStatisticsOptional.isPresent()){
            MovieStatistics movieStat = movieStatisticsOptional.get();
            movieStat.setImpressionCount(movieStat.getImpressionCount() + 1 );
            movieStatisticsRepository.save(movieStat);
            return;
        }

        MovieStatistics newMovieStat = new MovieStatistics(null, imdb, 1);
        movieStatisticsRepository.save(newMovieStat);
    }


}
