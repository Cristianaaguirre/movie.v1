package com.app.movie.ports.inputs.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class GenreCreateRequest {
   private String name;
   private String image;
}
