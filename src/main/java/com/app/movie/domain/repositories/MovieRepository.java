package com.app.movie.domain.repositories;

import com.app.movie.domain.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

   @Query("select case when count(m) > 0 then true else false end from Movie m where m.name = ?1")
   boolean existByName(String name);

   Movie findByName(String name);
}