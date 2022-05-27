package com.app.movie.domain.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Genre {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "genre_id")
   private Long id;

   @Column(nullable = false, unique = true)
   private String name;

   @Column(nullable = false)
   private String image;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Genre that = (Genre) o;
      return id.equals(that.id)
         && name.equals(that.name)
         && image.equals(that.image);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
