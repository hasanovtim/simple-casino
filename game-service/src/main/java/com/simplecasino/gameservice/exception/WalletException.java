package com.example.gameservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WalletException extends RuntimeException {
  public WalletException (String message) {
    super(message);
  }
}
