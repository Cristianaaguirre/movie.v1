package com.app.movie.ports.inputs.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class MovieDetails {
   private Long id;
   private String img;
   private String name;
   @JsonProperty("creation_date")
   private LocalDate localDate;
   private List<String> personages;
   private String genre;
}
