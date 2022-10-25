package com.kkulpa.moviesservice.backend.domain.mappers;

import com.kkulpa.moviesservice.backend.domain.DTOs.UserDetailsDto;
import com.kkulpa.moviesservice.backend.domain.UserDetails;

public class UserDetailsMapper {

    public static UserDetailsDto mapToDto(UserDetails userDetails){
        return new UserDetailsDto(
                userDetails.getId(),
                userDetails.getDisplayName(),
                userDetails.getBio(),
                userDetails.getPhotoLink()
        );
    }

}
