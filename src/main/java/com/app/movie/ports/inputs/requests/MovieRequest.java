package com.app.movie.ports.inputs.requests;

import com.app.movie.domain.models.Genre;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MovieRequest {
   @NotEmpty private String image;
   @NotEmpty private String name;
   @NotNull private Integer qualification;
   @NotEmpty private Genre genre;
}
