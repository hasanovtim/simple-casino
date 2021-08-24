package com.simplecasino.gameservice.request;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameRequest {
    @NotEmpty
    private String playerId;
    @NotNull
    private BigDecimal amount;
}
