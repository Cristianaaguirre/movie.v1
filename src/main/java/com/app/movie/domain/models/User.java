package com.app.movie.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "user")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@SQLDelete(sql = "UPDATE user SET is_active = false WHERE user_id = ?")
@Where(clause = "is_active = true")
public class User implements UserDetails{
   @Id
   @GeneratedValue(strategy = AUTO)
   @Column(name = "user_id")
   private Long id;

   @Column(nullable = false, unique = true)
   private String username;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false, unique = true, updatable = false)
   private String email;

   @Column(name = "creation_date", nullable = false, updatable = false)
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private LocalDateTime creationDate;

   @Column(name = "is_active", nullable = false)
   private Boolean isActive;

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "role_id")
   private Role roles;


   public Collection<? extends GrantedAuthority> getAuthorities() {
      if (this.roles != null) {
         return Collections.singleton(this.roles);
      }
      return Collections.emptySet();
   }

   public String getPassword() {
      return password;
   }

   public String getUsername() {
      return username;
   }

   public boolean isAccountNonExpired() {
      return true;
   }

   public boolean isAccountNonLocked() {
      return true;
   }

   public boolean isCredentialsNonExpired() {
      return true;
   }

   public boolean isEnabled() {
      return this.isActive;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return isActive == user.isActive &&
         id.equals(user.id) &&
         username.equals(user.username) &&
         password.equals(user.password) &&
         email.equals(user.email) &&
         creationDate.equals(user.creationDate) &&
         roles.equals(user.roles);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
