package org.bank.exception;

public class UserExistsException extends IllegalArgumentException {
  public UserExistsException(String message) {
    super(message);
  }
}
