package com.app.movie.domain.usercase;

import com.app.movie.domain.models.Genre;

import java.util.List;

public interface GenreService {

   Genre findById(Long id);
   Long create(Genre genre);
}
