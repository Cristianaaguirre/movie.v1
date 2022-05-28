package com.app.movie.ports.inputs.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MovieCreateRequest {
   @NotEmpty private String image;
   @NotEmpty private String name;
   @NotNull private Integer qualification;
   @NotNull private Long idGenre;
}
