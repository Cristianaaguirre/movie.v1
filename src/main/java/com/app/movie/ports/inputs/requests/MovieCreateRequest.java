package com.app.movie.ports.inputs.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MovieCreateRequest {
   @NotEmpty private String image;
   @NotEmpty private String name;
   @NotNull @Max(value = 5, message = "The maximum rating is 5") private Integer rating;
   @NotNull @JsonProperty("id_genre") private Long idGenre;
}
