package com.simplecasino.gameservice.service;

import com.simplecasino.gameservice.response.WalletResponse;
import com.simplecasino.gameservice.request.WalletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("wallet-service")
public interface WalletClient {
    String APPLICATION_JSON = "application/json";

    @GetMapping(consumes = APPLICATION_JSON, value = "/wallet/{playerId}")
    WalletResponse getWallet(@PathVariable("playerId") String playerId);

    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON,
            value = "/wallet/{id}/withdraw")
    WalletResponse withDraw(@PathVariable("id") String playerId,
                            @RequestBody WalletRequest request);
}
