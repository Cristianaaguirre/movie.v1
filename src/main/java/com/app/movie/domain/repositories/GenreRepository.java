package com.app.movie.domain.repositories;

import com.app.movie.domain.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

   Genre findByName(String name);

   @Query("select case when count(g) > 0 then true else false end from Genre g where g.name = ?1")
   boolean existByName(String name);
}