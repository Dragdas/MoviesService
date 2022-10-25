package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.domain.DTOs.UserDetailsDto;
import com.kkulpa.moviesservice.backend.domain.UserDetails;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.UserDetailsRepository;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;


    public UserDetailsDto updateUserDetails(UserDetailsDto newUserDetailsData, ApplicationUser requestingUser )
                                                                        throws  UserNotFoundException,
                                                                                UserNameIsNotAvailableException {

        UserDetails userDetailsForUpdate = userDetailsRepository.findByUsername(requestingUser.getUsername())
                                                                .orElseThrow(UserNotFoundException::new);

        if(displayNameIsTaken(newUserDetailsData) &&
            !userDetailsForUpdate.getDisplayName().equals(newUserDetailsData.getDisplayName()))
            throw new UserNameIsNotAvailableException();

        if(newUserDetailsData.getDisplayName() != null)
            userDetailsForUpdate.setDisplayName(newUserDetailsData.getDisplayName());

        if(newUserDetailsData.getBio() != null)
            userDetailsForUpdate.setBio(newUserDetailsData.getBio());

        if(newUserDetailsData.getPhotoLink() != null)
            userDetailsForUpdate.setPhotoLink(newUserDetailsData.getPhotoLink());

        userDetailsRepository.save(userDetailsForUpdate);

        return new UserDetailsDto(
                userDetailsForUpdate.getId(),
                userDetailsForUpdate.getDisplayName(),
                userDetailsForUpdate.getBio(),
                userDetailsForUpdate.getPhotoLink()
        );
    }

    private boolean displayNameIsTaken(UserDetailsDto newUserDetailsData) {
        return userDetailsRepository.existsByDisplayName(newUserDetailsData.getDisplayName());
    }

}
