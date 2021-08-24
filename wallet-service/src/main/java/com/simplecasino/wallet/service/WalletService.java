package com.simplecasino.wallet.service;

import com.simplecasino.wallet.entity.WalletEntity;
import com.simplecasino.wallet.exception.InsufficientFundsException;
import com.simplecasino.wallet.exception.PlayerAlreadyExistException;
import com.simplecasino.wallet.exception.PlayerNotFoundException;
import com.simplecasino.wallet.repository.WalletRepository;
import com.simplecasino.wallet.request.WalletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletEntity registerWallet(String playerId) {
        log.info(String.format("Register wallet for: %s", playerId));
        Optional<WalletEntity> player = walletRepository.findByPlayerId(playerId);
        if (player.isPresent()) {
            throw new PlayerAlreadyExistException(playerId);
        }

        return walletRepository.save(new WalletEntity(playerId, new BigDecimal("0")));
    }

    public WalletEntity getWallet(String playerId) {
        log.info(String.format("Get wallet for %s", playerId));
        return getWalletEntity(playerId);
    }

    @Transactional
    public WalletEntity deposit(String playerId, BigDecimal amount) {
        log.info(String.format("Deposit for %s", playerId));
        WalletEntity wallet = getWalletEntity(playerId);

        wallet.setBalance(wallet.getBalance().add(amount));

        return walletRepository.save(wallet);
    }

    public List<WalletEntity> getAllWallets() {
        log.info("Get all wallets");
        return walletRepository.findAll();
    }

    @Transactional
    public WalletEntity withdraw(String playerId, WalletRequest request) {
        log.info(String.format("Withdraw for %s", playerId));
        WalletEntity wallet = getWalletEntity(playerId);
        BigDecimal withdrawAmount = request.getAmount();

        if (wallet.getBalance().compareTo(withdrawAmount) < 0)
            throw new InsufficientFundsException(playerId);

        wallet.setBalance(wallet.getBalance().subtract(withdrawAmount));

        return walletRepository.save(wallet);
    }

    private WalletEntity getWalletEntity(String playerId) {
        return walletRepository.findByPlayerId(playerId)
            .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }
}
