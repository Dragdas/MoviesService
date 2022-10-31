package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieCommentDto;
import com.kkulpa.moviesservice.backend.domain.MovieComment;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.stream.Collectors;

public class MovieCommentMapper {

    public static MovieCommentDto mapToDto(MovieComment movieComment){

        return new MovieCommentDto(
                movieComment.getId(),
                movieComment.getAuthor().getId(),
                movieComment.getMovieDetails().getId(),
                movieComment.getComment()
        );
    }

    public static List<MovieCommentDto> mapListToDto(List<MovieComment> movieComments){

        return movieComments.stream()
                .map(MovieCommentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
