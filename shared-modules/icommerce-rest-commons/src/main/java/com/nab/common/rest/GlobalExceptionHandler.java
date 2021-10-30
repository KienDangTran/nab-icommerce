package com.nab.common.rest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private MessageSourceAccessor messageSourceAccessor;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
    binder.registerCustomEditor(String.class, stringTrimmer);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  private ResponseEntity<Object> handleHttpClientErrorException(
    HttpClientErrorException ex,
    WebRequest request
  ) {
    return handleExceptionInternal(
      ex,
      ex.getResponseBodyAsString(),
      ex.getResponseHeaders(),
      ex.getStatusCode(),
      request
    );
  }

  @ExceptionHandler(RepositoryConstraintViolationException.class)
  ResponseEntity<?> handleRepositoryConstraintViolationException(
    RepositoryConstraintViolationException ex
  ) {
    return ResponseEntity
      .badRequest()
      .body(new RepositoryConstraintViolationExceptionMessage(ex, messageSourceAccessor));
  }
}
