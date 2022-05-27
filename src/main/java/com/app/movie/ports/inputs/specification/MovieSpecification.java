package com.app.movie.ports.inputs.specification;

import com.app.movie.domain.models.Genre;
import com.app.movie.domain.models.Movie;
import com.app.movie.ports.inputs.requests.MovieFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

   public Specification<Movie> specification(MovieFilter aux) {
      return (root, query, criteriaBuilder) -> {

         List<Predicate> predicates = new ArrayList<>();

         if(aux.getName() != null)
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + aux.getName() + "%"));

         if(aux.getGenre() != null) {
            Join<Movie, Genre> join = root.join("genre");
            predicates.add(criteriaBuilder.like(join.get("name"), "%" + aux.getGenre() + "%"));
         }

         return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
      };
   }
}
