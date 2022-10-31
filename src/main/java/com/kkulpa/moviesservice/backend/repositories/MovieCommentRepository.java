package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.MovieComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieCommentRepository extends CrudRepository<MovieComment, Long> {
    
    List<MovieComment> findMovieCommentByAuthor_UserName(String username);

    List<MovieComment> findMovieCommentByMovieDetails_ImdbID(String imdbID);



}
