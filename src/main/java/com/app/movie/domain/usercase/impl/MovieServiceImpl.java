package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.InputException;
import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Genre;
import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.repositories.GenreRepository;
import com.app.movie.domain.repositories.MovieRepository;
import com.app.movie.domain.repositories.PersonageRepository;
import com.app.movie.domain.usercase.MovieService;
import com.app.movie.ports.inputs.requests.MovieFilter;
import com.app.movie.ports.inputs.specification.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

   private final MovieRepository movieRepository;
   private final GenreRepository genreRepository;
   private final PersonageRepository personageRepository;
   private final MovieSpecification specification;


   //======================Find======================//

   public Movie findById(Long id) {
      return movieRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("movie not found"));
   }

   public Movie findByName(String name) {
      if(movieRepository.existByName(name))
         throw new ResourceNotFoundException("movie not found");
      else return movieRepository.findByName(name);
   }

   public List<Movie> findAllByFilter(MovieFilter filter) {
      return movieRepository.findAll(specification.specification(filter));
   }

   public List<Movie> findAll() {
      return movieRepository.findAll();
   }


   //======================Create======================//

   @Transactional
   public Long create(Movie aux) {
      if(movieRepository.existByName(aux.getName()))
         throw new InputException("there is already a film with the same name");

      aux.setCreateAt(LocalDate.now());
      aux.setGenre(createGenre(aux.getGenre()));
      return movieRepository.save(aux).getId();
   }

   private Genre createGenre(Genre aux) {
      return Optional
         .ofNullable(genreRepository.findByName(aux.getName()))
         .orElse(aux);
   }

   //======================Update======================//

   @Transactional
   public void update(Long id, Movie aux) {

      if(movieRepository.existByName(aux.getName()))
         throw new InputException("the name already has taken");

      Movie movie = findById(id);
      movie.setImage(aux.getImage());
      movie.setName(aux.getName());
      movie.setQualification(aux.getQualification());

      movieRepository.save(movie);
   }

   public void addPersonage(Long idMovie, Long idPersonage) {
      Movie movie = findById(idMovie);
      Personage personage = personageRepository.findById(idPersonage)
         .orElseThrow(() -> new ResourceNotFoundException("personage not found"));

      if(movie.getPersonages().contains(personage))
         throw new InputException("the personage is already in the film");
      else {
         movie.getPersonages().add(personage);
         personage.getMovies().add(movie);
         movieRepository.save(movie);
         personageRepository.save(personage);
      }
   }

   //======================Delete======================//

   @Transactional
   public void delete(Long id) {
      if(movieRepository.existsById(id))
         movieRepository.deleteById(id);
      else throw new ResourceNotFoundException("movie not found");
   }

   public void removePersonage(Long idMovie, Long idPersonage) {
      Movie movie = findById(idMovie);
      Personage personage = personageRepository.findById(idPersonage)
         .orElseThrow(() -> new ResourceNotFoundException("personage not found"));

      if(!movie.getPersonages().contains(personage))
         throw new InputException("the personage is not in the film");
      else {
         movie.getPersonages().remove(personage);
         personage.getMovies().remove(movie);
         movieRepository.save(movie);
         personageRepository.save(personage);
      }
   }

}
