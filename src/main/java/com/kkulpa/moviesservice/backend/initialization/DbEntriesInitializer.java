package com.kkulpa.moviesservice.backend.initialization;

import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.DisplayNameNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNameIsNotAvailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.services.MovieService;
import com.kkulpa.moviesservice.backend.services.UserService;
import com.kkulpa.moviesservice.security.ApplicationUserRole;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import com.kkulpa.moviesservice.security.auth.ApplicationUserDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbEntriesInitializer {

    private final UserService userService;
    private final ApplicationUserDaoService applicationUserDaoService;
    private final MovieService movieService;



    @EventListener
    public void appReady(ApplicationReadyEvent event)
            throws Exception {

        User user1 = userService.addUser("a", "TestUserNick", "a");
        User user2 = userService.addUser("Adam", "Adam", "123");
        User user3 = userService.addUser("Ewa", "Ewa", "123");

        ApplicationUser u1 = applicationUserDaoService
                            .selectApplicationUserByUsername("a")
                            .orElseThrow(UserNotFoundException::new);

        ApplicationUser u2 = applicationUserDaoService
                .selectApplicationUserByUsername("Adam")
                .orElseThrow(UserNotFoundException::new);

        ApplicationUser u3 = applicationUserDaoService
                .selectApplicationUserByUsername("Ewa")
                .orElseThrow(UserNotFoundException::new);

        movieService.changeMovieRating(u1, "tt0325980", 8);
        movieService.changeMovieRating(u1, "tt0449088", 7);
        movieService.changeMovieRating(u1, "tt1790809", 4);
        movieService.changeMovieRating(u1, "tt2488496", 9);

        movieService.changeFavouriteStatus(u1, "tt0121766");
        movieService.changeFavouriteStatus(u1, "tt0449088");
        movieService.changeFavouriteStatus(u1, "tt0926084");


        movieService.changeMovieRating(u2, "tt0076759", 8);
        movieService.changeMovieRating(u2, "tt0080684", 7);
        movieService.changeMovieRating(u2, "tt0086190", 4);
        movieService.changeMovieRating(u2, "tt2488496", 9);

        movieService.changeFavouriteStatus(u2, "tt0449088");
        movieService.changeFavouriteStatus(u2, "tt0121766");


        movieService.changeMovieRating(u3, "tt1201607", 8);
        movieService.changeMovieRating(u3, "tt2488496", 7);
        movieService.changeMovieRating(u3, "tt0295297", 4);
        movieService.changeMovieRating(u3, "tt0304141", 9);

        movieService.changeFavouriteStatus(u3, "tt0304141");
        movieService.changeFavouriteStatus(u3, "tt0121766");


    }

}
