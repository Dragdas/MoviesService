package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    public List<MovieRating> ratings;
}
