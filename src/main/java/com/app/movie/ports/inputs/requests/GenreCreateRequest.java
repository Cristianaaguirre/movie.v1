package com.app.movie.ports.inputs.requests;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class GenreCreateRequest {
   private String name;
   private String image;
}
