package com.kkulpa.moviesservice.backend.controllers.secured;


import com.kkulpa.moviesservice.backend.domain.DTOs.UserDto;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
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

    @PutMapping(value = "/updateLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateLoginCredentials(@RequestBody UserDto userDto, Authentication authentication)
                                                                                throws UserNotFoundException {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        return ResponseEntity.ok(userService.updateLoginCredentials(user, userDto));
    }

    @PutMapping(value = "/updateDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateDetails(@RequestBody UserDto userDto){
        return ResponseEntity.ok(new UserDto(null, "stubName", "stub pass", null, null));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(){
        return ResponseEntity.ok().build();
    }





}
