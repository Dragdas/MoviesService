package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieRatingDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapToDto(User user){

        List<MovieRatingDto> ratings = user.getRatings().stream()
                                        .map(MovieRatingMapper::mapToDto)
                                        .collect(Collectors.toList());

        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                ratings,
                UserDetailsMapper.mapToDto(user.getUserDetails())
        );
    }
}
