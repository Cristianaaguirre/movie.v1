package com.app.movie.ports.inputs.controllers;

import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.usercase.impl.MovieServiceImpl;
import com.app.movie.ports.inputs.mapper.MovieMapper;
import com.app.movie.ports.inputs.requests.MovieFilter;
import com.app.movie.ports.inputs.requests.MovieRequest;
import com.app.movie.ports.inputs.requests.MovieUpdate;
import com.app.movie.ports.inputs.responses.MovieDetails;
import com.app.movie.ports.inputs.responses.MovieFilterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/movies")
@RequiredArgsConstructor
public class MovieController {

   private final MovieServiceImpl serviceImp;
   private final MovieMapper mapper;

   //=====================Getters=====================//

   @GetMapping
   public ResponseEntity<List<MovieFilterResponse>> findByFilter(@RequestParam(required = false) String name,
                                                                 @RequestParam(required = false) String genre) {
      List<MovieFilterResponse> list;
      if (name != null || genre != null) {
         MovieFilter filter = new MovieFilter(name, genre);
         list = mapper.toListFilter(serviceImp.findAllByFilter(filter));
      } else
         list = mapper.toListFilter(serviceImp.findAll());

      return ResponseEntity.ok().body(list);
   }

   @GetMapping(path = "/details")
   public ResponseEntity<MovieDetails> findByName(@RequestParam @NotEmpty String name) {
      return ResponseEntity.ok().body(toMovieDetails(serviceImp.findByName(name)));
   }

   //=====================Post=====================//

   @PostMapping(path = "/create")
   public ResponseEntity<Void> create(@RequestBody @Valid MovieRequest aux) {

      long id = serviceImp.create(mapper.toModel(aux));
      URI uri = ServletUriComponentsBuilder
         .fromCurrentRequest()
         .path("{id}").buildAndExpand(id)
         .toUri();
      return ResponseEntity.created(uri).build();
   }

   @PostMapping(path = "/{idMovie}/personage/{idPersonage}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void addPersonage(@PathVariable("idMovie") Long idMovie,
                            @PathVariable("idPersonage") Long idPersonage) {
      serviceImp.addPersonage(idMovie, idPersonage);
   }

   //=====================Put y Patch=====================//

   @PutMapping(path = "/update")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void update(@RequestParam @Valid Long id,
                      @RequestBody @Valid MovieUpdate aux) {
      serviceImp.update(id, mapper.updateToModel(aux));
   }

   //=====================Delete=====================//

   @DeleteMapping(path = "/delete")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@RequestParam @NotNull Long id) {
      serviceImp.delete(id);
   }


   @DeleteMapping(path = "/{idMovie}/personage/{idPersonage}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void removePersonage(@PathVariable("idMovie") Long idMovie,
                               @PathVariable("idPersonage") Long idPersonage) {
      serviceImp.removePersonage(idMovie, idPersonage);
   }


   //=====================Builder methods=====================//

   private MovieDetails toMovieDetails(Movie aux) {
      return MovieDetails.builder()
         .id(aux.getId())
         .img(aux.getImage())
         .name(aux.getName())
         .localDate(aux.getCreateAt())
         .personages(
            aux.getPersonages() == null
               ? null
               : aux.getPersonages()
               .stream().map(Personage::getName)
               .collect(Collectors.toList())
         )
         .genre(aux.getGenre().getName())
         .build();
   }
}
