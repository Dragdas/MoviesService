package com.kkulpa.moviesservice.backend.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name = "IMDB_ID")
    public String imdbID;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "GENRE")
    public String genre;

    @Column(name = "DIRECTOR")
    public String director;

    @Column(name = "PLOT")
    public String plot;

    @Column(name = "POSTER")
    public String poster;

    @Column(name = "BOXOFFICE")
    public String boxOffice;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MOVIE_STATISTICS", nullable = false)
    private MovieStatistics movieStatistics;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RATINGS")
    public List<MovieRating> ratings;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMMENTS")
    public List<MovieComment> comments;





}
