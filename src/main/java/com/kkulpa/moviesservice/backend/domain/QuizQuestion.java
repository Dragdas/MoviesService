package com.kkulpa.moviesservice.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUIZ_QUESTIONS")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERS", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "MOVIE", nullable = false)
    private MovieDetails movie;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "CORRECT_ANSWER")
    private String correctAnswer;

    @Column(name = "ANSWER_A")
    private String answerA;

    @Column(name = "ANSWER_B")
    private String answerB;

    @Column(name = "ANSWER_C")
    private String answerC;

}
