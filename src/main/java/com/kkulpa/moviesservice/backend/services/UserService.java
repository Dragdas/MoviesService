package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.domain.UserDetails;
import com.kkulpa.moviesservice.backend.domain.mappers.UserDetailsMapper;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.DisplayNameNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.UserDetailsRepository;
import com.kkulpa.moviesservice.backend.repositories.UserRepository;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public User getSessionUser(ApplicationUser requestingUser) throws UserNotFoundException {

        return userRepository.findByUserName(requestingUser.getUsername())
                .orElseThrow(UserNotFoundException::new);
    }

    public User addUser(String userName, String displayName, String password)
                                            throws  UserNameIsNotAvailableException,
                                                    DisplayNameNotAvailableException {

        if(userRepository.existsByUserName(userName))
            throw new UserNameIsNotAvailableException();
        if(userDetailsRepository.existsByDisplayName(displayName))
            throw new DisplayNameNotAvailableException();

        UserDetails userDetails = new UserDetails(null,
                displayName,
                "",
                "");

        User user = new User(null,
                userName,
                password,
                new ArrayList<>(),
                userDetails);


        return userRepository.save(user);
    }

    @Transactional
    public User updateLoginCredentials(ApplicationUser user, UserDto newUserData) throws UserNotFoundException {

        User userToBeUpdated = userRepository.findByUserName(user.getUsername())
                                    .orElseThrow(UserNotFoundException::new);

        if(!userRepository.existsByUserName(newUserData.getUserName()) && newUserData.getUserName() != null)
            userToBeUpdated.setUserName(newUserData.getUserName());

        if(newUserData.getPassword() != null)
            userToBeUpdated.setPassword(newUserData.getPassword());

        return userRepository.save(userToBeUpdated);
    }

    public void deleteUser(ApplicationUser requestingUser) throws UserNotFoundException {

        User userToBeRemoved = userRepository.findByUserName(requestingUser.getUsername())
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(userToBeRemoved);
    }

}
