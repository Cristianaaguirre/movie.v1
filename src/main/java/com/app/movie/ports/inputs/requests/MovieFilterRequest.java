package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MovieFilterRequest {
   @NotEmpty private String name;
   @NotEmpty private String genre;
   @NotEmpty @Size(max = 4) private String sort;
}
