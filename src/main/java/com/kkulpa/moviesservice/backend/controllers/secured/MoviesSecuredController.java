package com.kkulpa.moviesservice.backend.controllers.secured;


import com.kkulpa.moviesservice.backend.domain.DTOs.*;
import com.kkulpa.moviesservice.backend.domain.MovieDetails;
import com.kkulpa.moviesservice.backend.domain.MovieRating;
import com.kkulpa.moviesservice.backend.domain.SearchStatistics;
import com.kkulpa.moviesservice.backend.domain.mappers.MovieDetailsMappers;
import com.kkulpa.moviesservice.backend.domain.mappers.MovieRatingMapper;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.UserNotFoundException;
import com.kkulpa.moviesservice.backend.repositories.MovieRatingRepository;
import com.kkulpa.moviesservice.backend.services.MovieService;
import com.kkulpa.moviesservice.backend.services.StatisticsService;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/secured/movies")
@RequiredArgsConstructor
public class MoviesSecuredController {

    private final MovieService movieService;
    private final StatisticsService statisticsService;

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> getSearchResults(@RequestParam String title){

        statisticsService.addSearchEvent(title);
        return ResponseEntity.ok( movieService.getSearchResults(title));
    }

    @GetMapping("/getMovieDetails")
    public ResponseEntity<MovieDetailsDto> getMovieDetails(@RequestParam String imdbID)
                                                            throws MovieDetailsUnavailableException {

        statisticsService.addMovieDetailImpression(imdbID);
        return ResponseEntity.ok( movieService.getMovieDetails(imdbID));
    }

    @PutMapping(value = "/rating/fav")
    public ResponseEntity<Void> changeFavouriteStatus(Authentication authentication,
                                                    @RequestParam String imdbId)
                                                    throws Exception {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        movieService.changeFavouriteStatus(requestingUser, imdbId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/rating/rating")
    public ResponseEntity<Void> updateRating (Authentication authentication,
                                                        @RequestParam String imdbId,
                                                        @RequestParam int score)
                                                        throws Exception {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        movieService.changeMovieRating(requestingUser, imdbId, score);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/rating/fav")
    public ResponseEntity<List<MovieDetailsDto>> getUsersFavouriteMovies(Authentication authentication)
                                                                            throws UserNotFoundException {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        List<MovieDetails> favouriteMovies = movieService.getUsersFavouriteMovies(requestingUser);

        return ResponseEntity.ok(MovieDetailsMappers.mapListToDto(favouriteMovies));
    }

    @GetMapping("/rating/rating")
    public ResponseEntity<List<MovieDetailsDto>> getUsersRatedFilms(Authentication authentication)
                                                                            throws UserNotFoundException {

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();
        List<MovieDetails> ratedMovies = movieService.getUsersRatedMovies(requestingUser);

        return ResponseEntity.ok(MovieDetailsMappers.mapListToDto(ratedMovies));
    }

    @GetMapping("/rating/fav/ranking")
    public ResponseEntity<List<MovieDetailsDto>> getFavouriteCountRanking(){

        List<MovieDetails> ranking = movieService.getMovieRankingByFavouritesCount();

        return ResponseEntity.ok(MovieDetailsMappers.mapListToDto(ranking));
    }

    @GetMapping("/rating/rating/ranking")
    public ResponseEntity<Map<MovieDetailsDto, Double>> getRatingRanking(){

        Map<MovieDetailsDto, Double> ranking = movieService.getMovieRankingByRating();

        return ResponseEntity.ok(ranking);
    }

    @PostMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieCommentDto> addComment(@RequestBody MovieCommentDto movieCommentDto){
        return ResponseEntity.ok(new MovieCommentDto());
    }

    @PutMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieCommentDto> editComment(@RequestBody MovieCommentDto movieCommentDto){
        return ResponseEntity.ok(new MovieCommentDto());
    }

    @DeleteMapping(value = "/comment")
    public ResponseEntity<Void> deleteComment(@RequestParam Long commentId){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/comment/byMovie")
    public ResponseEntity<List<MovieCommentDto>> getCommentsByMovie(@RequestParam String imdbId){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/comment/byUser")
    public ResponseEntity<List<MovieCommentDto>> getCommentsByUser(@RequestParam Long userId){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/searchStats")
    public ResponseEntity<List<String>> getTopSearchedPhrases(){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/viewStats")
    public ResponseEntity<Long> getMovieProfileViewCount(@RequestParam String imdbId){
        return ResponseEntity.ok(5L);
    }






}
