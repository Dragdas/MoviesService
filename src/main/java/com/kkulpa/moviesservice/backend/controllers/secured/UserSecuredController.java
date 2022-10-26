package com.kkulpa.moviesservice.backend.controllers.secured;


import com.kkulpa.moviesservice.backend.domain.DTOs.UserDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.domain.UserDetails;
import com.kkulpa.moviesservice.backend.domain.mappers.UserDetailsMapper;
import com.kkulpa.moviesservice.backend.domain.mappers.UserMapper;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.services.UserDetailsService;
import com.kkulpa.moviesservice.backend.services.UserService;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secured/users")
@RequiredArgsConstructor
public class UserSecuredController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<UserDto> getUserInfo(Authentication authentication) throws UserNotFoundException {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        User user = userService.getSessionUser(requestingUser);

        return ResponseEntity.ok(UserMapper.mapToDto(user));
    }

    @PutMapping(value = "/updateLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateLoginCredentials(@RequestBody UserDto userDto, Authentication authentication)
                                                                                throws UserNotFoundException {
        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        User userUpdated = userService.updateLoginCredentials(requestingUser, userDto);

        return ResponseEntity.ok(UserMapper.mapToDto(userUpdated));
    }

    @PutMapping(value = "/updateDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDto> updateUserDetails(@RequestBody UserDetailsDto newUserDetailsData,
                                                            Authentication authentication)
                                                                                throws UserNotFoundException,
                                                                                UserNameIsNotAvailableException {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        UserDetails userDetailsUpdated = userDetailsService.updateUserDetails(newUserDetailsData, requestingUser);

        return ResponseEntity.ok(UserDetailsMapper.mapToDto(userDetailsUpdated));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(Authentication authentication) throws UserNotFoundException {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        userService.deleteUser(requestingUser);

        return ResponseEntity.ok().build();
    }





}
