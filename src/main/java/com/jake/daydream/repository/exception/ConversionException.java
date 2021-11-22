/* (C) 2021 */
package com.jake.daydream.repository.exception;

public class ConversionException extends RuntimeException {

  public ConversionException() {}

  public ConversionException(String message) {
    super(message);
  }

  public ConversionException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConversionException(Throwable cause) {
    super(cause);
  }
}
