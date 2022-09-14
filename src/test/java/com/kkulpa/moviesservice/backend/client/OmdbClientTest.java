package com.kkulpa.moviesservice.backend.client;

import com.kkulpa.moviesservice.backend.domain.DTOs.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OmdbClientTest {

    @Autowired
    OmdbClient omdbClient;

    @Test
    void movieSearchTest(){
        //when
        List<MovieDto> searchResult = omdbClient.searchForMovies("pirates", "");
        //searchResult.forEach(System.out::println);
        //then
        assertTrue(searchResult.size()>0);

    }

    @Test
    void getMovieDetails(){


        try {
            System.out.println(omdbClient.getMovieDetails("tt5126922"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}