package com.app.movie.ports.inputs.controllers;

import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.usercase.MovieService;
import com.app.movie.ports.inputs.mapper.MovieMapper;
import com.app.movie.ports.inputs.requests.MovieCreateRequest;
import com.app.movie.ports.inputs.requests.MovieFilterRequest;
import com.app.movie.ports.inputs.requests.MovieUpdateRequest;
import com.app.movie.ports.inputs.responses.MovieDetailsResponse;
import com.app.movie.ports.inputs.responses.MovieFilterResponse;
import io.swagger.annotations.ApiOperation;
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

import static com.app.movie.ports.constant.ApiConstant.MOVIE_URI;

@RestController
@RequestMapping(MOVIE_URI)
@RequiredArgsConstructor
public class MovieController {

   private final MovieService serviceImp;
   private final MovieMapper mapper;

   //=====================Getters=====================//

   @ApiOperation("Display a list of films with a filter")
   @GetMapping
   public ResponseEntity<List<MovieFilterResponse>> findByFilter(@RequestParam(required = false) String name,
                                                                 @RequestParam(required = false) String genre) {
      List<MovieFilterResponse> list;
      if (name != null || genre != null) {
         MovieFilterRequest filter = new MovieFilterRequest(name, genre);
         list = mapper.movieFilterResponseToMovieList(serviceImp.findAllByFilter(filter));
      } else
         list = mapper.movieFilterResponseToMovieList(serviceImp.findAll());

      return ResponseEntity.ok().body(list);
   }

   @ApiOperation("Show the details of a movie")
   @GetMapping(path = "/details")
   public ResponseEntity<MovieDetailsResponse> findByName(@RequestParam @NotEmpty String name) {
      return ResponseEntity.ok().body(toMovieDetails(serviceImp.findByName(name)));
   }

   //=====================Post=====================//

   @ApiOperation("Create a movie")
   @PostMapping(path = "/create")
   public ResponseEntity<Void> create(@RequestBody @Valid MovieCreateRequest request) {

      long id = serviceImp.create(mapper.movieCreateRequestToMovie(request));
      URI uri = ServletUriComponentsBuilder
         .fromCurrentRequest()
         .path("{id}").buildAndExpand(id)
         .toUri();
      return ResponseEntity.created(uri).build();
   }

   @ApiOperation("Add a personage to a movie")
   @PostMapping(path = "/{idMovie}/personage/{idPersonage}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void addPersonage(@PathVariable("idMovie") Long idMovie,
                            @PathVariable("idPersonage") Long idPersonage) {
      serviceImp.addPersonage(idMovie, idPersonage);
   }

   //=====================Put y Patch=====================//

   @ApiOperation("Update the name, image and qualification of a film")
   @PutMapping(path = "/update")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void update(@RequestParam @Valid Long id,
                      @RequestBody @Valid MovieUpdateRequest request) {
      serviceImp.update(id, mapper.movieUpdateRequestToMovie(request));
   }

   //=====================Delete=====================//

   @ApiOperation("Delete a movie")
   @DeleteMapping(path = "/delete")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@RequestParam @NotNull Long id) {
      serviceImp.delete(id);
   }


   @ApiOperation("Remove a personage to a movie")
   @DeleteMapping(path = "/{idMovie}/personage/{idPersonage}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void removePersonage(@PathVariable("idMovie") Long idMovie,
                               @PathVariable("idPersonage") Long idPersonage) {
      serviceImp.removePersonage(idMovie, idPersonage);
   }


   //=====================Builder methods=====================//

   private MovieDetailsResponse toMovieDetails(Movie movie) {
      return MovieDetailsResponse.builder()
         .id(movie.getId())
         .img(movie.getImage())
         .name(movie.getName())
         .localDate(movie.getCreateAt())
         .rating(movie.getRating())
         .personages(
            movie.getPersonages() == null
               ? null
               : movie.getPersonages()
               .stream().map(Personage::getName)
               .collect(Collectors.toList())
         )
         .genre(movie.getGenre().getName())
         .build();
   }
}
