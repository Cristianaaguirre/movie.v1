package com.app.movie.ports.inputs.controllers;

import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.usercase.PersonageService;
import com.app.movie.ports.inputs.mapper.PersonageMapper;
import com.app.movie.ports.inputs.requests.PersonageRequest;
import com.app.movie.ports.inputs.requests.PersonageFilterRequest;
import com.app.movie.ports.inputs.responses.PersonageDetailsResponse;
import com.app.movie.ports.inputs.responses.PersonageFilterResponse;
import io.swagger.annotations.ApiOperation;
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

import static com.app.movie.ports.constant.ApiConstant.PERSONAGE_URI;

@RestController
@RequestMapping(PERSONAGE_URI)
@RequiredArgsConstructor
@Validated
public class PersonageController {

   private final PersonageService service;
   private final PersonageMapper mapper;

   //=====================Getters=====================//

   @ApiOperation("Display a list of personage with a filter")
   @GetMapping(path = "filter")
   public ResponseEntity<List<PersonageFilterResponse>> findByFilter(@RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) Integer age,
                                                                     @RequestParam(required = false) Integer weigth,
                                                                     @RequestParam(required = false) String movie) {
      List<PersonageFilterResponse> list;

      if (name != null || age != null || weigth != null || movie != null) {
         PersonageFilterRequest filter = new PersonageFilterRequest(name, age, weigth, movie);
         list = mapper.toListFilter(service.filterPersonage(filter));
      } else
         list = mapper.toListFilter(service.findAll());

      return ResponseEntity.ok().body(list);
   }

   @ApiOperation("Show the details of a personage")
   @GetMapping(path = "/details")
   public ResponseEntity<PersonageDetailsResponse> findByName(@RequestParam String name) {
      return ResponseEntity.ok().body(
         toResponse(service.findByName(name))
      );
   }

   //=====================Post=====================//

   @ApiOperation("Create a personage")
   @PostMapping(path = "/create")
   public ResponseEntity<Void> createPersonage(@RequestBody @Valid PersonageRequest request) {

      Personage personage = mapper.toModel(request);
      long id = service.create(personage);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("/{id}").buildAndExpand(id).toUri();

      return ResponseEntity.created(uri).build();
   }

   //=====================Puts=====================//

   @ApiOperation("Update image, name, age, weigth and history of a personage")
   @PutMapping(path = "/update")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updatedPersonage(@RequestParam Long id, @RequestBody @Valid PersonageRequest aux) {
      service.update(id, mapper.toModel(aux));
   }

   //=====================Deletes=====================//

   @ApiOperation("Delete a personage")
   @DeleteMapping(path = "/delete")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteCharacter(@RequestParam Long id) {
      service.delete(id);
   }


   //=====================Builders method=====================//

   private PersonageDetailsResponse toResponse(Personage aux) {
      return PersonageDetailsResponse.builder()
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
