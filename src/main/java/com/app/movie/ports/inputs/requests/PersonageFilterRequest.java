package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PersonageFilterRequest {
   @NotEmpty private String name;
   @NotNull private Integer age;
   @NotNull private Integer weigth;
   @NotEmpty private String movie;
}
