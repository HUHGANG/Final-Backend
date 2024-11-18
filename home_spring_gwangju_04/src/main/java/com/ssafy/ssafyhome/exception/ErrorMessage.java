package com.ssafy.ssafyhome.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {
  private final LocalDateTime timestamp = LocalDateTime.now();
  private final String message;

  public ErrorMessage(String message) {
    this.message = message;
  }
}
