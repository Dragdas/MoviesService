package com.kkulpa.moviesservice.backend.domain.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieRatingDto {

    public MovieRatingDto(MovieRating movieRating){
        this.id = movieRating.getId();
        this.reviewerId = movieRating.getReviewer().getId();
        this.movieDetailsId = movieRating.getMovieDetails().getId();
        this.rating = movieRating.getRating();
        this.isFavourite = movieRating.isFavourite();
    }

    private long id;
    private long reviewerId;
    private long movieDetailsId;
    private int rating;
    private boolean isFavourite;
}
