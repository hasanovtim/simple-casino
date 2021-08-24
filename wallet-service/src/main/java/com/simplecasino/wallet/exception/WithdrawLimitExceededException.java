package com.simplecasino.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class WithdrawLimitExceededException extends RuntimeException {
  public WithdrawLimitExceededException(String playerId) {
    super(String.format("Withdraw limit exceeded for player %s ", playerId));
  }
}
