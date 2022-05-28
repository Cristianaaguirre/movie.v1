package com.app.movie.domain.usercase;

import com.app.movie.domain.models.Movie;
import com.app.movie.ports.inputs.requests.MovieFilterRequest;

import java.util.List;

public interface MovieService {
   Movie findById(Long id);
   Movie findByName(String name);
   List<Movie> findAll();
   List<Movie> findAllByFilter(MovieFilterRequest filter);
   Long create(Movie aux);
   void update(Long id, Movie aux);
   void delete(Long id);
   void addPersonage(Long idMovie, Long idPersonage);
   void removePersonage(Long idMovie, Long idPersonage);
}
