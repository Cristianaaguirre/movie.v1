package com.app.movie.ports.inputs.controllers;

import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.usercase.impl.PersonageServiceImpl;
import com.app.movie.ports.inputs.mapper.PersonageMapper;
import com.app.movie.ports.inputs.requests.PersonageFilter;
import com.app.movie.ports.inputs.requests.PersonageRequest;
import com.app.movie.ports.inputs.responses.PersonageDetails;
import com.app.movie.ports.inputs.responses.PersonageFilterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/characters")
@RequiredArgsConstructor
@Validated
public class PersonageController {

   private final PersonageServiceImpl service;
   private final PersonageMapper mapper;

   //=====================Getters=====================//

   @GetMapping
   public ResponseEntity<List<PersonageFilterResponse>> findByFilter(@RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) Integer age,
                                                                     @RequestParam(required = false) Integer weigth,
                                                                     @RequestParam(required = false) String movie) {
      List<PersonageFilterResponse> list;

      if (name != null || age != null || weigth != null || movie != null) {
         PersonageFilter filter = new PersonageFilter(name, age, weigth, movie);
         list = mapper.toListFilter(service.filterPersonage(filter));
      } else list = mapper.toListFilter(service.findAll());

      return ResponseEntity.ok().body(list);
   }

   @GetMapping(path = "/details")
   public ResponseEntity<PersonageDetails> findByName(String name) {
      return ResponseEntity.ok().body(
         toResponse(service.findByName(name))
      );
   }

   //=====================Post=====================//


   @PostMapping(path = "/create")
   public ResponseEntity<Void> createPersonage(@RequestBody @Valid PersonageRequest aux) {

      long id = service.create(mapper.toModel(aux));

      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("/{id}").buildAndExpand(id)
         .toUri();
      return ResponseEntity.created(uri).build();
   }

   //=====================Puts=====================//


   @PutMapping(path = "/update")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updatedPersonage(@RequestParam Long id, @RequestBody @Valid PersonageRequest aux) {
      service.update(id, mapper.toModel(aux));
   }

   //=====================Deletes=====================//


   @DeleteMapping(path = "/delete")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteCharacter(@RequestParam Long id) {
      service.delete(id);
   }


   //=====================Builders method=====================//

   private PersonageDetails toResponse(Personage aux) {
      return PersonageDetails.builder()
         .id(aux.getId())
         .image(aux.getImage())
         .name(aux.getName())
         .age(aux.getAge())
         .weigth(aux.getWeigth())
         .history(aux.getHistory())
         .movies(
            aux.getMovies() == null
               ? null
               : aux.getMovies()
               .stream()
               .map(Movie::getName)
               .collect(Collectors.toList())
         )
         .build();
   }
}
