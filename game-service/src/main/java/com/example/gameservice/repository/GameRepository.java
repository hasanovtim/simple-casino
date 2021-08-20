package com.example.gameservice.repository;

import com.example.gameservice.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, String> {
    List<GameEntity> findByGameId(String gameId);
}
