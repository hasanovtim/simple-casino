package com.example.gameservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class GameEntity implements Serializable {
    public GameEntity(String gameId, String playerId, BigDecimal amount) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.betAmount = amount;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String gameId;

    private String playerId;

    private BigDecimal betAmount;
}
