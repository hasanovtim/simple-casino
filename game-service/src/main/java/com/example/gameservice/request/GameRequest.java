package com.example.gameservice.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class GameRequest {
    @NotNull
    private String playerId;
    @NotNull
    private BigDecimal amount;

    public GameRequest() {}

    public GameRequest(String playerId, BigDecimal amount) {
        this.playerId = playerId;
        this.amount = amount;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
