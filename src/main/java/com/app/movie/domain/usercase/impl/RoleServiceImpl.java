package com.app.movie.domain.usercase.impl;

import com.app.movie.domain.models.Role;
import com.app.movie.domain.repositories.RoleRepository;
import com.app.movie.domain.usercase.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

   private final RoleRepository roleRepository;

   @Override
   public void saveRol(Role role) {
      roleRepository.save(role);
   }
}
