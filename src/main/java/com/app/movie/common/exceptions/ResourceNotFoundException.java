package com.app.movie.common.exceptions;

public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String message) {
      super(message);
   }
}
