package com.app.movie.ports.inputs.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class MovieDetailsResponse {
   private Long id;
   private String img;
   private String name;
   @JsonProperty("creation_date")
   private LocalDate localDate;
   private Integer rating;
   private List<String> personages;
   private String genre;
}
