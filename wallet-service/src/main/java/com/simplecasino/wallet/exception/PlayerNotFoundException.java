package com.simplecasino.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PlayerNotFoundException extends RuntimeException {
  public PlayerNotFoundException(String playerId) {
    super(String.format("Player %s not found", playerId));
  }
}
