package com.app.movie.domain.usercase;

import com.app.movie.domain.models.Personage;
import com.app.movie.ports.inputs.requests.PersonageFilterRequest;

import java.util.List;

public interface PersonageService {

   Personage findById(Long id);
   Personage findByName(String name);
   List<Personage> filterPersonage(PersonageFilterRequest request);
   List<Personage> findAll();
   Long create(Personage aux);
   void update(Long id, Personage aux);
   void delete(Long id);
}
