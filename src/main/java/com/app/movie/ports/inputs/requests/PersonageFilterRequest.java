package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PersonageFilterRequest {
   private String name;
   private Integer age;
   private Integer weigth;
   private String movie;
}
