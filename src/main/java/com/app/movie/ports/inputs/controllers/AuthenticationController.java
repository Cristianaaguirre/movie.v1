package com.app.movie.ports.inputs.controllers;

import com.app.movie.common.security.service.AuthenticationService;
import com.app.movie.domain.models.User;
import com.app.movie.domain.usercase.UserService;
import com.app.movie.ports.inputs.mapper.UserMapper;
import com.app.movie.ports.inputs.requests.AuthenticationRequest;
import com.app.movie.ports.inputs.requests.UserRequestRegistration;
import com.app.movie.ports.inputs.responses.AuthenticationResponse;
import com.app.movie.ports.inputs.responses.UserDetailsResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.net.URI;

import static com.app.movie.ports.constant.ApiConstant.AUTH_URI;

@RestController
@RequestMapping(AUTH_URI)
@RequiredArgsConstructor
public class AuthenticationController {

   private final UserService userService;
   private final UserMapper mapper;
   private final AuthenticationService authenticationService;

   //================Register================//

   @ApiOperation("Register a user")
   @PostMapping(path = "/register")
   public ResponseEntity<UserDetailsResponse> register(@RequestBody @Valid UserRequestRegistration registration) {
      User user = userService.register(mapper.userRegistrationToUser(registration));

      UserDetailsResponse response = mapper.userToUserDetails(user);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("/{id}").buildAndExpand(user.getId()).toUri();

      return ResponseEntity.created(uri).body(response);
   }

   //================Login================//

   @ApiOperation("Log in to the Api")
   @PostMapping(path = "/login")
   public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
      String jwt = authenticationService.login(request.getUsername(), request.getPassword());
      return ResponseEntity.ok(new AuthenticationResponse(jwt));
   }

   @ApiIgnore
   @GetMapping(path = "/me")
   public ResponseEntity<UserDetailsResponse> getUserDetails(@AuthenticationPrincipal User user) {
      UserDetailsResponse userDetailsResponse = mapper.userToUserDetails(user);
      return ResponseEntity.status(HttpStatus.OK).body(userDetailsResponse);
   }
}
