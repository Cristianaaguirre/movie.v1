package com.app.movie.config;

import com.app.movie.common.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.app.movie.common.security.util.PasswordEncoder.encoder;
import static com.app.movie.ports.constant.ApiConstant.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final UserDetailsService userDetailsService;
   private final JwtFilter jwtFilter;

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {

      http.csrf().disable();
      http.authorizeRequests()
         .antMatchers(AUTH_URI + "*").permitAll()
         .antMatchers(PERSONAGE_URI + "**", MOVIE_URI +  "**", GENRE_URI + "**").hasAuthority("USER")
         .anyRequest().anonymous()
         .and().exceptionHandling()
         .and().sessionManagement().sessionCreationPolicy(STATELESS);

      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

   }
}
