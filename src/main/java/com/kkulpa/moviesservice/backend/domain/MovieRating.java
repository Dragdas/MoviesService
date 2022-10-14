package com.kkulpa.moviesservice.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MOVIE_RATINGS")
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERS")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "MOVIE_DETAILS")
    private MovieDetails movieDetails;

    @Column(name = "RATING", nullable = true)
    private int rating;

    @Column(name = "IS_FAVOURITE", nullable = true)
    private boolean isFavourite;
}
