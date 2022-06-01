package com.app.movie.domain.repositories;

import com.app.movie.domain.models.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonageRepository extends JpaRepository<Personage, Long> , JpaSpecificationExecutor<Personage> {

   boolean existsByName(String name);

   Personage findByName(String name);
}