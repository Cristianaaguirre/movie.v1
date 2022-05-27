package com.app.movie.domain.repositories;

import com.app.movie.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByUsername(String username);

   @Query("select case when count(u) > 0 then true else false end from User u where u.username = ?1")
   boolean existByUsername(String username);

   @Query("select case when count(u) > 0 then true else false end " +
      "from User u where u.email = :email or u.username = :username")
   boolean existByEmailAndUsername(String email, String username);

}