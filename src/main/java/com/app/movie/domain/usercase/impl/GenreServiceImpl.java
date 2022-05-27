package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Genre;
import com.app.movie.domain.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl {

   private final GenreRepository repository;

   public Genre findById(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("genre not found"));
   }

   public Genre create(Genre aux) {
      return Optional
         .ofNullable(repository.findByName(aux.getName()))
         .orElse(repository.save(aux));
   }
}
