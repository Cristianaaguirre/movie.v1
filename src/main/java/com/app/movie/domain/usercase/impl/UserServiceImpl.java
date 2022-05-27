package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.AlreadyExistsException;
import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Role;
import com.app.movie.domain.models.User;
import com.app.movie.domain.repositories.RoleRepository;
import com.app.movie.domain.repositories.UserRepository;
import com.app.movie.domain.usercase.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static com.app.movie.common.security.util.PasswordEncoder.encoder;


@Service
@RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;

   //================Login================//

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return findByUsername(username);
   }

   //================Find================//


   public User findByUsername(String username) {
      if(!userRepository.existByUsername(username))
         throw new ResourceNotFoundException("user not found");
      else return userRepository.findByUsername(username);
   }


   //================Register and Update================//

   @Transactional
   public User register(User user) {
      if(userRepository.existByEmailAndUsername(user.getEmail(), user.getUsername()))
         throw new AlreadyExistsException("the email or username already has taken");

      Role rol = roleRepository.findByName("USER");
      String password = encoder().encode(user.getPassword());

      user.setPassword(password);
      user.setRoles(rol);
      user.setCreationDate(LocalDateTime.now());

      userRepository.save(user);
      return user;
   }

   @Transactional
   public void updateUser(Long id, User aux) {
      User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
      user.setUsername(aux.getUsername());
      user.setPassword(encoder().encode(aux.getPassword()));
      userRepository.save(user);
   }

   //================Set Rol================//

   @Transactional
   public void saveRole(Role role) {
      roleRepository.save(role);
   }

   //================Delete================//

   @Transactional
   public void deleteUser(Long id) {
      if(userRepository.existsById(id)) userRepository.deleteById(id);
      else throw new ResourceNotFoundException("user not found");
   }
}
