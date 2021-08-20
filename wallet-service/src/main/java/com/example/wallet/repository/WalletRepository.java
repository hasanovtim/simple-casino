package com.example.wallet.repository;

import com.example.wallet.model.WalletEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {
  Optional<WalletEntity> findByPlayerId(String playerId);
}
