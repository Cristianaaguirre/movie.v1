package com.app.movie.ports.inputs.specification;

import com.app.movie.domain.models.Movie;
import com.app.movie.domain.models.Personage;
import com.app.movie.ports.inputs.requests.PersonageFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonageSpecification {

   public Specification<Personage> specification(PersonageFilter request) {

      return (root, query, criteriaBuilder) -> {

         List<Predicate> predicates = new ArrayList<>();

         if (request.getName() != null)
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));

         else if (request.getAge() != null)
            predicates.add(criteriaBuilder.like(root.get("age"), "%" + request.getAge() + "%"));

         else if (request.getWeigth() != null)
            predicates.add(criteriaBuilder.like(root.get("weigth"), "%" + request.getWeigth() + "%"));

         else if (request.getMovie() != null) {
            Join<Personage, Movie> join = root.join("movies");
            predicates.add(criteriaBuilder.like(join.get("name"), "%" + request.getMovie() + "%"));
         }

         return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
      };
   }
}
