package com.kkulpa.moviesservice.backend.errorHandling;

import com.kkulpa.moviesservice.backend.errorHandling.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleTitleNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>("User with provided ID does not exist in database",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameIsNotAvailableException.class)
    public ResponseEntity<Object> handleUserNameTaken(UserNameIsNotAvailableException exception){
        return new ResponseEntity<>("Provided user name is already taken",
                HttpStatus.IM_USED);
    }

    @ExceptionHandler(DisplayNameNotAvailableException.class)
    public ResponseEntity<Object> handleDisplayNameTaken(DisplayNameNotAvailableException exception){
        return new ResponseEntity<>("Provided display name is already taken",
                HttpStatus.IM_USED);
    }

    @ExceptionHandler(MovieDetailsUnavailableException.class)
    public ResponseEntity<Object> handleMissingMovieDetails(MovieDetailsUnavailableException exception){
        return new ResponseEntity<>("Requested movie details are unavailable",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Object> handleCommentNotFound(CommentNotFoundException exception){
        return new ResponseEntity<>("Requested comment is unavailable",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleCommentNotFound(AccessDeniedException exception){
        return new ResponseEntity<>("You are unauthorized!",
                HttpStatus.FORBIDDEN);
    }








}
