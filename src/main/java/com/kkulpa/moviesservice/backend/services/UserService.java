package com.kkulpa.moviesservice.backend.services;

import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User addUser(UserDto userDto){
        User user = new User(null,
                userDto.getUserName(),
                userDto.getPassword(),
                Collections.emptyList());

        return userRepository.save(user);
    }

}
