package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PersonageRequest {
   @NotEmpty private String image;
   @NotEmpty @Size(min = 4) private String name;
   @NotNull private Integer age;
   @NotNull @Size(max = 3) private Integer weigth;
   @NotEmpty private String history;
}
