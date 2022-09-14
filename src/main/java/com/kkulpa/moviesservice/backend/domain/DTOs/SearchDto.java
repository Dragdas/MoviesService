package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SearchDto {

    @JsonProperty("Search")
    public ArrayList<MovieDto> films;

}
