package com.app.movie.ports.inputs.controllers;

import com.app.movie.common.security.service.AuthenticationService;
import com.app.movie.domain.models.User;
import com.app.movie.domain.usercase.UserService;
import com.app.movie.ports.inputs.mapper.UserMapper;
import com.app.movie.ports.inputs.requests.UserRegistration;
import com.app.movie.ports.inputs.responses.AuthenticationResponse;
import com.app.movie.ports.inputs.responses.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;
   private final UserMapper mapper;
   private final AuthenticationService authenticationService;

   //================Register================//

   @PostMapping(path = "/register")
   public ResponseEntity<UserDetailsResponse> register(@RequestBody @Valid UserRegistration registration) {
      User user = userService.register(mapper.userRegistrationToUser(registration));

      UserDetailsResponse response = mapper.userToUserDetails(user);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("/{id}").buildAndExpand(user.getId()).toUri();

      return ResponseEntity.created(uri).body(response);
   }

   @PostMapping(path = "/login")
   public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
      String jwt = authenticationService.login(username, password);
      return ResponseEntity.ok(new AuthenticationResponse(jwt));
   }
}
