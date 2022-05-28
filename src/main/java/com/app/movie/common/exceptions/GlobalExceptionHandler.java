package com.app.movie.common.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   protected ResponseEntity<Object> handlerResourceNotFound(RuntimeException ex, WebRequest request) {
      return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
   }

   @ExceptionHandler(AlreadyExistsException.class)
   protected ResponseEntity<Object> handlerEmailExist(IllegalStateException ex, WebRequest request) {
      return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
   }

   @ExceptionHandler(UserException.class)
   protected ResponseEntity<Object> handlerUser(RuntimeException ex, WebRequest request) {
      return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
   }
}
