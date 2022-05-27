package com.app.movie.domain.repositories;

import com.app.movie.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   Role findByName(String role);
}