package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.AlreadyExistsException;
import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Genre;
import com.app.movie.domain.repositories.GenreRepository;
import com.app.movie.domain.usercase.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

   private final GenreRepository repository;

   public Genre findById(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("genre not found"));
   }

   public Long create(Genre request) {
      if(repository.existByName(request.getName()))
         throw new AlreadyExistsException("there is a genre with the same name");
      else return repository.save(request).getId();
   }
}
