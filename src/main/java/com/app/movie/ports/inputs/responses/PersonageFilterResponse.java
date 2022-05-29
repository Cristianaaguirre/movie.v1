package com.app.movie.ports.inputs.responses;

import lombok.*;

@Data
@Builder
public class PersonageFilterResponse {
   private String image;
   private String name;
}
