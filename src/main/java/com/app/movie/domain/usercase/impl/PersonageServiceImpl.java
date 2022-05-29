package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.AlreadyExistsException;
import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.repositories.PersonageRepository;
import com.app.movie.domain.usercase.PersonageService;
import com.app.movie.ports.inputs.requests.PersonageFilterRequest;
import com.app.movie.ports.inputs.specification.PersonageSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonageServiceImpl implements PersonageService {

   private final PersonageRepository repository;
   private final PersonageSpecification personageSpecification;

   //======================Find======================//

   public Personage findByName(String name) {
      if(repository.existByName(name)) return repository.findByName(name);
      else throw new ResourceNotFoundException("character not found");
   }

   public List<Personage> filterPersonage(PersonageFilterRequest request) {
      return repository.findAll(personageSpecification.specification(request));
   }

   public List<Personage> findAll() { return repository.findAll(); }

   //===============Create and Update===============//

   @Transactional
   public Long create(Personage personage) {
      if(repository.existByName(personage.getName()))
         throw new AlreadyExistsException("there is a personage with the same name");

      String name = personage.getName().toLowerCase();
      String history = personage.getHistory().toLowerCase();

      personage.setName(name);
      personage.setHistory(history);

      return repository.save(personage).getId();
   }

   @Transactional
   public void update(Long id, Personage update) {
      if(repository.existByName(update.getName()))
         throw new AlreadyExistsException("there is a personage with the same name");

      Personage personage = repository.findById(update.getId())
         .orElseThrow(() -> new ResourceNotFoundException("personage not found"));

      personage.setImage(update.getImage());
      personage.setName(update.getName());
      personage.setAge(update.getAge());
      personage.setWeigth(update.getWeigth());
      personage.setHistory(update.getHistory());

      repository.save(personage);
   }

   //===============Delete===============//

   @Transactional
   public void delete(Long id) {
      if(repository.existsById(id)) repository.deleteById(id);
      else throw new ResourceNotFoundException("personage not found");
   }
}
