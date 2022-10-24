package com.kkulpa.moviesservice.backend.controllers.open;

import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.DisplayNameNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/users")
@RequiredArgsConstructor
public class UserPublicController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestParam String userName,
                                                @RequestParam String displayName,
                                                @RequestParam String password)
                                                throws UserNameIsNotAvailableException,
                                                       DisplayNameNotAvailableException {

        User user = userService.addUser(userName, displayName, password);

        return ResponseEntity.ok( new UserDto(user) );
    }



}
