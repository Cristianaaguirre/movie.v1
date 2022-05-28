package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.AlreadyExistsException;
import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.repositories.PersonageRepository;
import com.app.movie.domain.usercase.PersonageService;
import com.app.movie.ports.inputs.requests.PersonageFilterRequest;
import com.app.movie.ports.inputs.specification.PersonageSpecification;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonageServiceImpl implements PersonageService {

   private final PersonageRepository repository;
   private final PersonageSpecification personageSpecification;

   //======================Find======================//

   public Personage findById(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("character not found"));
   }

   public Personage findByName(String name) {
      if(!repository.existByName(name)) throw new ResourceNotFoundException("character not found");
      else return repository.findByName(name);
   }

   public List<Personage> filterPersonage(PersonageFilterRequest request) {
      return repository.findAll(personageSpecification.specification(request));
   }

   public List<Personage> findAll() { return repository.findAll(); }

   //===============Create and Update===============//

   @Transactional
   public Long create(@NotNull Personage aux) {
      if(repository.existByName(aux.getName())) throw new AlreadyExistsException("registered name");
      return repository.save(aux).getId();
   }

   @Transactional
   public void update(Long id, Personage aux) {
      if(repository.existByName(aux.getName()))
         throw new AlreadyExistsException("there is a Personage with the same name");

      Personage personage = findById(id);

      personage.setImage(aux.getImage().toLowerCase());
      personage.setName(aux.getName().toLowerCase());
      personage.setAge(aux.getAge());
      personage.setWeigth(aux.getWeigth());
      personage.setHistory(aux.getHistory().toLowerCase());

      repository.save(personage);
   }

   //===============Delete===============//

   @Transactional
   public void delete(Long id) {
      if(repository.existsById(id)) repository.deleteById(id);
      else throw new ResourceNotFoundException("personage not found");
   }
}
