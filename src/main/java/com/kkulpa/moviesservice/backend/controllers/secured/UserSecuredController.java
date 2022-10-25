package com.kkulpa.moviesservice.backend.controllers.secured;


import com.kkulpa.moviesservice.backend.domain.DTOs.UserDetailsDto;
import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.services.UserDetailsService;
import com.kkulpa.moviesservice.backend.services.UserService;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secured/users")
@RequiredArgsConstructor
public class UserSecuredController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<UserDto> getUserInfo(Authentication authentication) throws UserNotFoundException {

        return ResponseEntity.ok(userService.getSessionUser(authentication));
    }

    @PutMapping(value = "/updateLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateLoginCredentials(@RequestBody UserDto userDto, Authentication authentication)
                                                                                throws UserNotFoundException {
        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        return ResponseEntity.ok(userService.updateLoginCredentials(requestingUser, userDto));
    }

    @PutMapping(value = "/updateDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDto> updateUserDetails(@RequestBody UserDetailsDto newUserDetailsData,
                                                            Authentication authentication)
                                                                                throws UserNotFoundException,
                                                                                UserNameIsNotAvailableException {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();

        return ResponseEntity.ok(userDetailsService.updateUserDetails(newUserDetailsData, requestingUser));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(){
        return ResponseEntity.ok().build();
    }





}
