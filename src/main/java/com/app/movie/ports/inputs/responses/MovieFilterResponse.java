package com.app.movie.ports.inputs.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class MovieFilterResponse {
   @JsonProperty("name")
   private String name;
   @JsonProperty("creation_date")
   private LocalDate createAt;
}
