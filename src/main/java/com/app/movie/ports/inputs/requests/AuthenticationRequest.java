package com.app.movie.ports.inputs.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AuthenticationRequest {
   @NotEmpty private String username;
   @NotEmpty private String password;
}
