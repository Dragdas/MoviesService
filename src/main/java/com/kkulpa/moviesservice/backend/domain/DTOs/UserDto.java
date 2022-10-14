package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.domain.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    public UserDto(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.userDetails = new UserDetailsDto(user.getUserDetails());
        this.ratings = user.getRatings()
                        .stream()
                        .map(MovieRatingDto::new)
                        .collect(Collectors.toList());
    }


    private Long id;
    private String userName;
    private String password;
    public List<MovieRatingDto> ratings;
    public UserDetailsDto userDetails;

}
