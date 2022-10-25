package com.kkulpa.moviesservice.backend.controllers.secured;


import com.kkulpa.moviesservice.backend.domain.DTOs.*;
import com.kkulpa.moviesservice.backend.errorHandling.exceptions.MovieDetailsUnavailableException;
import com.kkulpa.moviesservice.backend.services.MovieService;
import com.kkulpa.moviesservice.security.auth.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/secured/movies")
@RequiredArgsConstructor
public class MoviesSecuredController {

    private final MovieService movieService;

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> getSearchResults(@RequestParam String title){

        return ResponseEntity.ok( movieService.getSearchResults(title));
    }

    @GetMapping("/getMovieDetails")
    public ResponseEntity<MovieDetailsDto> getMovieDetails(@RequestParam String imdbID)
                                                            throws MovieDetailsUnavailableException {

        return ResponseEntity.ok( movieService.getMovieDetails(imdbID));
    }

    @PutMapping(value = "/rating/fav")
    public ResponseEntity<MovieRatingDto> updateFavouriteStatus(Authentication authentication,
                                                                @RequestParam String imdbId){

        ApplicationUser requestingUser = (ApplicationUser) authentication.getPrincipal();

        return ResponseEntity.ok(new MovieRatingDto());
    }

    @PutMapping(value = "/rating/rating")
    public ResponseEntity<MovieRatingDto> updateRating (@RequestBody Long userId,
                                                        @RequestParam String imdbId,
                                                        @RequestParam int score){
        return ResponseEntity.ok(new MovieRatingDto());
    }

    @GetMapping("/rating/fav")
    public ResponseEntity<List<MovieDto>> getUsersFavouriteMovies(@RequestParam Long userId){
        return ResponseEntity.ok(List.of(new MovieDto("stub title","2022", "stub id")));
    }

    @GetMapping("/rating/rating")
    public ResponseEntity<List<MovieDto>> getUsersRatedFilms(@RequestParam Long userId){
        return ResponseEntity.ok(List.of(new MovieDto("stub title","2022", "stub id")));
    }

    @GetMapping("/rating/fav/ranking")
    public ResponseEntity<List<MovieDto>> getFavouriteCountRanking(Authentication authentication){
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();

        return ResponseEntity.ok(List.of(new MovieDto("stub title","2022", "stub id")));
    }

    @GetMapping("/rating/rating/ranking")
    public ResponseEntity<List<MovieDto>> getRatingRanking(){
        return ResponseEntity.ok(List.of(new MovieDto("stub title","2022", "stub id")));
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
