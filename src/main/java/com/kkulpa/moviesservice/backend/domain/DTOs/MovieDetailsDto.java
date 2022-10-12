package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class MovieDetailsDto {

    @JsonProperty("imdbID")
    public String imdbID;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Genre")
    public String genre;
    @JsonProperty("Director")
    public String director;
    @JsonProperty("Plot")
    public String plot;
    @JsonProperty("Poster")
    public String poster;
    @JsonProperty("BoxOffice")
    public String boxOffice;

}
