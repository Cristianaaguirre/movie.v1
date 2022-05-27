package com.app.movie.domain.repositories;

import com.app.movie.domain.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

   @Query("select gen from Genre gen where gen.name = ?1")
   Genre findByName(String name);
}