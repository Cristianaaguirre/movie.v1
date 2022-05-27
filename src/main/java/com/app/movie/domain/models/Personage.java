package com.app.movie.domain.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Personage {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "personage_id")
   private Long id;

   @Column(nullable = false)
   private String image;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private Integer age;

   @Column(nullable = false)
   private Integer weigth;

   @Type(type = "org.hibernate.type.TextType")
   @Column(nullable = false)
   private String history;

   @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
   @JoinTable(
      name = "personage_movie",
      joinColumns = {@JoinColumn(name = "personage_id")},
      inverseJoinColumns = {@JoinColumn(name = "movie_id")}
   )
   private List<Movie> movies;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Personage entity = (Personage) o;
      return id.equals(entity.id)
         && image.equals(entity.image)
         && name.equals(entity.name)
         && age.equals(entity.age)
         && weigth.equals(entity.weigth)
         && history.equals(entity.history) && Objects.equals(movies, entity.movies);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
