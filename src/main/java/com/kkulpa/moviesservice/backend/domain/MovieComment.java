package com.kkulpa.moviesservice.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMENTS")
public class MovieComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "AUTHOR", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "MOVIE_DETAILS", nullable = false)
    private MovieDetails movieDetails;

    @Column(name = "COMMENT")
    private String comment;

}
