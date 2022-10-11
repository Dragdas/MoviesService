package com.kkulpa.moviesservice.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MOVIE_DETAILS")
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RATINGS")
    public List<MovieRating> ratings;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MOVIE_STATISTICS", nullable = false)
    private MovieStatistics movieStatistics;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMMENTS")
    public List<MovieComment> comments;

}
