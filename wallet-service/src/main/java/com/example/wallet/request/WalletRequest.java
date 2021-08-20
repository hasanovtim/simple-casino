package com.example.wallet.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WalletRequest {
    @NotNull
    private BigDecimal amount;

    public WalletRequest() {}

    public WalletRequest(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
