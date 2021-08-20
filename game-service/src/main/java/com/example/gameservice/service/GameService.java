package com.example.gameservice.service;

import com.example.gameservice.model.GameEntity;
import com.example.gameservice.response.GameResponse;
import com.example.gameservice.response.WalletResponse;
import com.example.gameservice.repository.GameRepository;
import com.example.gameservice.request.GameRequest;
import com.example.gameservice.request.WalletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final WalletClientWrapper walletClient;

    public GameResponse placeBet(String gameId, GameRequest gameRequest) {
        WalletResponse wallet = walletClient.getWallet(gameRequest.getPlayerId());
        BigDecimal betAmount = gameRequest.getAmount();
        if (wallet.getBalance().compareTo(betAmount) < 0) {
            throw new RuntimeException("Not enough balance");
        }

        walletClient.withDraw(gameRequest.getPlayerId(), new WalletRequest(betAmount));

        gameRepository.save(new GameEntity(gameId, gameRequest.getPlayerId(), betAmount));

        return new GameResponse(gameRequest.getPlayerId(), gameId, betAmount);
    }

    public List<GameResponse> getBets(String gameId) {
          return gameRepository.findByGameId(gameId)
                  .stream()
                  .map(g -> new GameResponse(g.getPlayerId(),
                          g.getGameId(),
                          g.getBetAmount()))
                  .collect(Collectors.toList());
    }

    public List<GameResponse> allBets() {
        return gameRepository.findAll()
                .stream()
                .map(g -> new GameResponse(g.getPlayerId(),
                        g.getGameId(),
                        g.getBetAmount()))
                .collect(Collectors.toList());
    }
}
