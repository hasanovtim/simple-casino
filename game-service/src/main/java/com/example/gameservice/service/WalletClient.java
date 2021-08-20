package com.example.gameservice.service;

import com.example.gameservice.response.WalletResponse;
import com.example.gameservice.request.WalletRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "wallet", url = "http://localhost:8083/wallet/")
public interface WalletClient {
    String APPLICATION_JSON = "application/json";

    @GetMapping(consumes = APPLICATION_JSON, value = "{id}")
    WalletResponse getWallet(@PathVariable("id") String playerId);

    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON,
            value = "{id}/withdraw")
    WalletResponse withDraw(@PathVariable("id") String playerId,
                            @RequestBody WalletRequest request);
}
