package com.app.movie.domain.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Movie {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "movie_id")
   private Long id;

   @Column(nullable = false)
   private String image;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false, name = "create_date")
   private LocalDate createAt;

   private Integer qualification;

   @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
   @ToString.Exclude
   private List<Personage> personages;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "genre_id")
   private Genre genre;


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Movie entity = (Movie) o;
      return Objects.equals(id, entity.id)
         && image.equals(entity.image)
         && name.equals(entity.name)
         && createAt.equals(entity.createAt)
         && qualification.equals(entity.qualification)
         && genre.equals(entity.genre);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
