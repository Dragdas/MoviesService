package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkulpa.moviesservice.backend.domain.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetailsDto {

    public UserDetailsDto(UserDetails userDetails){
        this.id = userDetails.getId();
        this.displayName = userDetails.getDisplayName();
        this.bio = userDetails.getBio();
        this.photoLink = userDetails.getPhotoLink();
    }

    private Long id;
    private String displayName;
    private String bio;
    private String photoLink;

}
