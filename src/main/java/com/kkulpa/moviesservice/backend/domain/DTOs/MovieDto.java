package com.kkulpa.moviesservice.backend.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;
    public String imdbID;
}
