package com.example.gameservice.service;

import com.example.gameservice.model.GameEntity;
import com.example.gameservice.response.GameResponse;
import com.example.gameservice.response.WalletResponse;
import com.example.gameservice.repository.GameRepository;
import com.example.gameservice.request.GameRequest;
import com.example.gameservice.request.WalletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    private final WalletClient walletClient;

    public GameResponse placeBet(String gameId, GameRequest gameRequest) {
        String playerId = gameRequest.getPlayerId();
        BigDecimal betAmount = gameRequest.getAmount();
        log.info(String.format("Place a bet. Game: %s Player: %s Amount: %s", gameId,
            playerId, gameRequest.getAmount()));
        WalletResponse wallet = walletClient.getWallet(playerId);
        if (wallet.getBalance().compareTo(betAmount) < 0) {
            throw new RuntimeException("Not enough balance");
        }

        walletClient.withDraw(playerId, new WalletRequest(betAmount));

        gameRepository.save(new GameEntity(gameId, playerId, betAmount));

        return new GameResponse(playerId, gameId, betAmount);
    }

    public List<GameResponse> getBets(String gameId) {
        log.info(String.format("Get bets for game: %s", gameId));
        return gameRepository.findByGameId(gameId)
                  .stream()
                  .map(g -> new GameResponse(g.getPlayerId(),
                          g.getGameId(),
                          g.getBetAmount()))
                  .collect(Collectors.toList());
    }

    public List<GameResponse> allBets() {
        log.info("Get all bets");
        return gameRepository.findAll()
                .stream()
                .map(g -> new GameResponse(g.getPlayerId(),
                        g.getGameId(),
                        g.getBetAmount()))
                .collect(Collectors.toList());
    }
}
