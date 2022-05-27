package com.app.movie.domain.repositories;

import com.app.movie.domain.models.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonageRepository extends JpaRepository<Personage, Long> , JpaSpecificationExecutor<Personage> {

   @Query("select case when count(p) > 0 then true else false end from Personage p where p.name = ?1")
   boolean existByName(String name);

   Personage findByName(String name);
}