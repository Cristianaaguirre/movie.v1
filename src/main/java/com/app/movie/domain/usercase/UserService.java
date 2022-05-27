package com.app.movie.domain.usercase;

import com.app.movie.domain.models.Role;
import com.app.movie.domain.models.User;

public interface UserService {
   User findByUsername(String username);
   User register(User user);
   void updateUser(Long id, User user);
   void saveRole(Role role);
   void deleteUser(Long id);

}
