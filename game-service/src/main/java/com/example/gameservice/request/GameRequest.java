package com.example.gameservice.request;

import com.example.gameservice.validation.UUID;
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
    @UUID
    private String playerId;
    @NotNull
    private BigDecimal amount;
}
