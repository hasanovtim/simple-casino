package com.example.wallet.service;

import com.example.wallet.model.WalletEntity;
import com.example.wallet.repository.WalletRepository;
import com.example.wallet.request.WalletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletEntity register(String playerId) {
        Optional<WalletEntity> player = walletRepository.findByPlayerId(playerId);
        if (player.isPresent()) {
            throw new RuntimeException(String.format("Player %s already exist", playerId));
        }

        return walletRepository.save(new WalletEntity(playerId, new BigDecimal("0")));
    }

    public WalletEntity getBalance(String playerId) {
          return getWalletEntity(playerId);
    }

    public WalletEntity deposit(String playerId, BigDecimal amount) {
        WalletEntity wallet = getWalletEntity(playerId);

        wallet.setBalance(wallet.getBalance().add(amount));

        return walletRepository.save(wallet);
    }

    public List<WalletEntity> getAllWallets() {
        return walletRepository.findAll();
    }

    public WalletEntity withdraw(String playerId, WalletRequest request) {
        WalletEntity wallet = getWalletEntity(playerId);
        BigDecimal withdrawAmount = request.getAmount();

        if (wallet.getBalance().compareTo(withdrawAmount) < 0)
            throw new RuntimeException(String.format("Insufficient funds for player %s ", playerId));

        wallet.setBalance(wallet.getBalance().subtract(withdrawAmount));

        return walletRepository.save(wallet);
    }

    private WalletEntity getWalletEntity(String playerId) {
        return walletRepository.findByPlayerId(playerId)
            .orElseThrow(() -> new RuntimeException(String.format("Player %s is not exist", playerId)));
    }
}
