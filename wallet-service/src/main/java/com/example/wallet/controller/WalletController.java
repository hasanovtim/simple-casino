package com.example.wallet.controller;

import com.example.wallet.model.WalletEntity;
import com.example.wallet.request.WalletRequest;
import com.example.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;
    private static final String APPLICATION_JSON = "application/json";

    @PostMapping(path = "/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<WalletEntity> register(@PathVariable("id") String playerId) {
        return ResponseEntity.ok(walletService.register(playerId));
    }

    @GetMapping(path = "/", produces = APPLICATION_JSON)
    public ResponseEntity<List<WalletEntity>> allWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }


    @GetMapping(path = "/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<WalletEntity> balance(@PathVariable("id") String playerId) {

        return ResponseEntity.ok(walletService.getBalance(playerId));
    }

    @PostMapping(value = "/{id}/deposit", produces = APPLICATION_JSON)
    public ResponseEntity<WalletEntity> deposit(@PathVariable("id") String playerId,
                                                @RequestBody WalletRequest request) {

        return ResponseEntity.ok(walletService.deposit(playerId, request.getAmount()));
    }

    @PostMapping(path = "/{id}/withdraw", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<WalletEntity> withdraw(@PathVariable("id") String playerId,
                                                 @RequestBody WalletRequest request) {

        return ResponseEntity.ok(walletService.withdraw(playerId, request));
    }
}
