package com.kkulpa.moviesservice.backend.initialization;

import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.DisplayNameNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbEntriesInitializer {

    private final UserService userService;



    @EventListener
    public void appReady(ApplicationReadyEvent event) throws UserNameIsNotAvailableException, DisplayNameNotAvailableException {

        User user1 = userService.addUser("a", "TestUserNick", "a");
        User user2 = userService.addUser("Adam", "Adam", "123");
        User user3 = userService.addUser("Ewa", "Ewa", "123");









    }

}
