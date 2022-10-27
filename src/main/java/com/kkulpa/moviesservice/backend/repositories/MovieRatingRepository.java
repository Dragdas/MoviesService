package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieRatingRepository extends CrudRepository<MovieRating, Long> {

    @Query(value = "SELECT rating FROM MovieRating rating where rating.reviewer.userName like :username and rating.movieDetails.imdbID like :imdbId")
    Optional<MovieRating> findMovieRatingByUserNameAndIMDB(@Param("username")String username,
                                                           @Param("imdbId")String imdbId);



    @Query(value = "SELECT mr.movieDetails " +
                    "FROM MovieRating mr " +
                    "WHERE mr.isFavourite IS TRUE " +
                    "GROUP BY mr.movieDetails.imdbID " +
                    "ORDER BY COUNT(mr.id) DESC")
    List<MovieDetails> findMostFavouriteMovies();


}
