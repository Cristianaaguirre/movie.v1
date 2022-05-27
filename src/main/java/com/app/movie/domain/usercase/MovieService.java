package com.app.movie.domain.usercase;

import com.app.movie.domain.models.Movie;

import java.util.List;

public interface MovieService {
   Movie findById(Long id);
   List<Movie> findAll();
   Long create(Movie aux);
   void update(Long id, Movie aux);
   void delete(Long id);
}
