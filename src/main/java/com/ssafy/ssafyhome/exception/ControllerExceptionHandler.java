package com.ssafy.ssafyhome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ErrorMessage badRequestHandler(Exception ex, WebRequest request) {
    return new ErrorMessage(ex.getMessage());
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public ErrorMessage unauthorizedHandler(Exception ex, WebRequest request) {
    return new ErrorMessage(ex.getMessage());
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(ForbiddenException.class)
  public ErrorMessage forbiddenHandler(Exception ex, WebRequest request) {
    return new ErrorMessage(ex.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorMessage serverErrorHandler(Exception ex, WebRequest request) {
    ex.printStackTrace();
    return new ErrorMessage("INTERNAL_SERVER_ERROR");
  }
}
