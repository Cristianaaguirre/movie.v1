package com.app.movie.ports.inputs.requests;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserRequestRegistration {
   @NotEmpty private String username;
   @NotEmpty @Size(min = 8, max = 20) private String password;
   @NotEmpty @Email private String email;
   @NotNull private Boolean isActive;
}
