package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UpdateUser {
   @NotEmpty private String username;
   @NotEmpty @Size(min = 8, max = 20) private String password;
}
