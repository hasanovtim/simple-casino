package com.example.gameservice.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WalletRequest {
    public WalletRequest() {}

    public WalletRequest(BigDecimal amount) {
        this.amount = amount;
    }

    @NotNull
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
