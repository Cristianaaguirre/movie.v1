package com.app.movie.common.security.service;

import com.app.movie.common.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

   private final AuthenticationManager authenticationManager;
   private final JwtUtil jwtUtil;

   public String login(String username, String password) {
      Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      UserDetails userDetails = (UserDetails) auth.getPrincipal();
      return jwtUtil.generateToken(userDetails);
   }
}
