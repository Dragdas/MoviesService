package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.domain.UserDetails;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.DisplayNameNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.UserDetailsRepository;
import com.kkulpa.moviesservice.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public User getUser(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
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

}
