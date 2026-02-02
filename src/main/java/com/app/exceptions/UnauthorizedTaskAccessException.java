package com.app.exceptions;

public class UnauthorizedTaskAccessException extends RuntimeException {
  public UnauthorizedTaskAccessException(String message) {
    super(message);
  }
}
