package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MovieFilterRequest {
   @NotEmpty private String name;
   @NotEmpty private String genre;
}
