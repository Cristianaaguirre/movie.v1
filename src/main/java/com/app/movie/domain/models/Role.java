package com.app.movie.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Role implements GrantedAuthority {

   @Id
   @Column(name = "role_id")
   @GeneratedValue(strategy = AUTO)
   private Long id;
   @Column(nullable = false, updatable = false)
   private String name;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Role role = (Role) o;
      return id.equals(role.id) && name.equals(role.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String getAuthority() {
      return this.name;
   }
}
