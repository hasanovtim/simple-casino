package com.example.gameservice.service;

import com.example.gameservice.request.WalletRequest;
import com.example.gameservice.response.WalletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Allow to mock feign client in tests
 */
@Component
@RequiredArgsConstructor
public class WalletClientWrapper {
    private final WalletClient feignClient;

    public WalletResponse getWallet(String playerId) {
        return feignClient.getWallet(playerId);
    }

    public WalletResponse withDraw(String playerId,
                             WalletRequest request) {
        return feignClient.withDraw(playerId, request);
    }
}
