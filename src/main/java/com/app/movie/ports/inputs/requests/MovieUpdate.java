package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MovieUpdate {
   @NotEmpty private String image;
   @NotEmpty private String name;
   @NotNull private Integer qualification;
}
