package com.app.movie.domain.usercase;

import com.app.movie.domain.models.Genre;

import java.util.List;

public interface GenreService {

   Genre findById(Long id);
   Genre save(Genre aux);
   Genre update(Long id, Genre aux);
   List<Genre> findAll();
   void delete(Long id);
}
