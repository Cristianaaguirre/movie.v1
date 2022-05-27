package com.app.movie.ports.inputs.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDetailsResponse {
   private String username;
   private String email;
   @JsonProperty("creation_date")
   private LocalDateTime creationDate;
}
