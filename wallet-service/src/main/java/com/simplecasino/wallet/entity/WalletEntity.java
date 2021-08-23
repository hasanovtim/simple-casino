package com.simplecasino.wallet.entity;

import javax.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity implements Serializable {
    public WalletEntity(String playerId, BigDecimal balance) {
        this.playerId = playerId;
        this.balance = balance;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String playerId;

    private BigDecimal balance;
}
