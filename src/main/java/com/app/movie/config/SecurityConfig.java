package com.app.movie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.app.movie.common.security.util.PasswordEncoder.encoder;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final UserDetailsService userDetailsService;

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

      http.csrf().disable().sessionManagement().sessionCreationPolicy(STATELESS);
      http.authorizeRequests()
         .antMatchers("/auth/login", "/auth/register").permitAll()
         .antMatchers("/characters/**", "/movies/**").hasAuthority("USER");
      http.authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .httpBasic();

   }
}
