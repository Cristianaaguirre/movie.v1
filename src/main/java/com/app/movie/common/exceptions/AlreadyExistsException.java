package com.app.movie.common.exceptions;

public class AlreadyExistsException extends IllegalStateException{
   public AlreadyExistsException(String massage) { super(massage); }
}
