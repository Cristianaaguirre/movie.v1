package com.app.movie.ports.inputs.responses;

import lombok.*;

@Builder
@Data
public class PersonageFilterResponse {
   private String image;
   private String name;
}
