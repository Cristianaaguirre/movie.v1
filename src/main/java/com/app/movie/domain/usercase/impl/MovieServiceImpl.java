package com.app.movie.domain.usercase.impl;

import com.app.movie.common.exceptions.AlreadyExistsException;
import com.app.movie.common.exceptions.ResourceNotFoundException;
import com.app.movie.domain.models.Genre;
import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.domain.repositories.GenreRepository;
import com.app.movie.domain.repositories.MovieRepository;
import com.app.movie.domain.repositories.PersonageRepository;
import com.app.movie.domain.usercase.MovieService;
import com.app.movie.ports.inputs.requests.MovieFilterRequest;
import com.app.movie.ports.inputs.specification.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

   private final MovieRepository movieRepository;
   private final GenreRepository genreRepository;
   private final PersonageRepository personageRepository;
   private final MovieSpecification specification;


   //======================Find======================//

   public Movie findByName(String name) {
      if(movieRepository.existByName(name)) return movieRepository.findByName(name);
      else throw new ResourceNotFoundException("movie not found");
   }

   public List<Movie> findAllByFilter(MovieFilterRequest filter) {
      return movieRepository.findAll(specification.specification(filter));
   }

   public List<Movie> findAll() {
      return movieRepository.findAll();
   }


   //======================Create======================//

   @Transactional
   public Long create(Movie movie) {
      if(movieRepository.existByName(movie.getName()))
         throw new AlreadyExistsException("there is already a film with the same name");

      String name = movie.getName().toLowerCase();
      LocalDate date = LocalDate.now();
      Genre genre = genreRepository.findById(movie.getGenre().getId())
         .orElseThrow(()-> new ResourceNotFoundException("genre not found"));

      movie.setName(name);
      movie.setCreateAt(date);
      movie.setGenre(genre);
      return movieRepository.save(movie).getId();
   }


   //======================Update======================//

   @Transactional
   public void update(Long id, Movie aux) {

      if(movieRepository.existByName(aux.getName()))
         throw new AlreadyExistsException("the name already has taken");

      Movie movie = movieRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("movie not found"));

      movie.setImage(aux.getImage());
      movie.setName(aux.getName());
      movie.setRating(aux.getRating());

      movieRepository.save(movie);
   }

   public void addPersonage(Long idMovie, Long idPersonage) {

      Movie movie = movieRepository.findById(idMovie)
         .orElseThrow(() -> new ResourceNotFoundException("movie not found"));

      Personage personage = personageRepository.findById(idPersonage)
         .orElseThrow(() -> new ResourceNotFoundException("personage not found"));

      if(movie.getPersonages().contains(personage))
         throw new AlreadyExistsException("the personage is already in the film");
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
      if(movieRepository.existsById(id)) movieRepository.deleteById(id);
      else throw new ResourceNotFoundException("movie not found");
   }

   public void removePersonage(Long idMovie, Long idPersonage) {

      Movie movie = movieRepository.findById(idMovie)
         .orElseThrow(() -> new ResourceNotFoundException("movie not found"));

      Personage personage = personageRepository.findById(idPersonage)
         .orElseThrow(() -> new ResourceNotFoundException("personage not found"));

      if(!movie.getPersonages().contains(personage))
         throw new ResourceNotFoundException("the personage is not in the film");
      else {
         movie.getPersonages().remove(personage);
         personage.getMovies().remove(movie);
         movieRepository.save(movie);
         personageRepository.save(personage);
      }
   }

}
