package com.simplecasino.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DepositLimitExceededException extends RuntimeException {
  public DepositLimitExceededException(String playerId) {
    super(String.format("Deposit limit exceeded for player %s ", playerId));
  }
}
