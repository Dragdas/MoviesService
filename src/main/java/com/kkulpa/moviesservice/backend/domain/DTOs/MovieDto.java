package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class MovieDto {

    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;
    public String imdbID;
}
