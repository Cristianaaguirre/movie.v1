package com.app.movie.ports.inputs.controllers;

import com.app.movie.domain.models.Genre;
import com.app.movie.domain.usercase.GenreService;
import com.app.movie.ports.inputs.mapper.GenreMapper;
import com.app.movie.ports.inputs.requests.GenreCreateRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.app.movie.ports.constant.ApiConstant.GENRE_URI;

@RestController
@RequestMapping(GENRE_URI)
@RequiredArgsConstructor
public class GenreController {

   private final GenreService genreService;
   private final GenreMapper mapper;

   @ApiOperation("Create a genre")
   @PostMapping(path = "/create")
   public ResponseEntity<Void> creteGenre(@RequestBody GenreCreateRequest request) {
      Genre genre = mapper.GenreCreateRequestToGenre(request);
      long id = genreService.create(genre);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
         .path("/{id}").buildAndExpand(id).toUri();
      return ResponseEntity.created(uri).build();
   }
}
