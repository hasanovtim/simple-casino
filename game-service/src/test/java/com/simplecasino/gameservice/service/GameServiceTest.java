package com.example.gameservice.service;

import com.example.gameservice.entity.GameEntity;
import com.example.gameservice.repository.GameRepository;
import com.example.gameservice.request.GameRequest;
import com.example.gameservice.response.GameResponse;
import com.example.gameservice.response.WalletResponse;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest {
    private static final String PLAYER_ID = "123";
    private static final String GAME_ID = "5000";
    private static final BigDecimal BALANCE = new BigDecimal("10.00");
    private static final BigDecimal BET = new BigDecimal("3.00");

    @Mock
    private WalletClient walletClient;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    public void shouldPlaceABet() {
        Mockito.when(walletClient.getWallet(PLAYER_ID)).thenReturn(new WalletResponse(PLAYER_ID, BALANCE));

        Mockito.when(gameRepository.save(new GameEntity(GAME_ID, PLAYER_ID, BET)))
                .thenReturn(new GameEntity(GAME_ID, PLAYER_ID, BALANCE));

        GameResponse game = gameService.placeBet(GAME_ID, new GameRequest(PLAYER_ID, BET));
        assertThat(game.getPlayerId(), equalTo(PLAYER_ID));
        assertThat(game.getGameId(), equalTo(GAME_ID));
        assertThat(game.getBetAmount(), equalTo(BET));
        verify(gameRepository).save(new GameEntity(GAME_ID, PLAYER_ID, BET));
    }

    @Test
    public void getBetByGameId() {
        Mockito.when(gameRepository.findByGameId(GAME_ID))
            .thenReturn(Collections.singletonList(new GameEntity(GAME_ID, PLAYER_ID, BET)));

        List<GameEntity> bets = gameService.getBets(GAME_ID);
        GameEntity game = bets.get(0);
        assertThat(game.getPlayerId(), equalTo(PLAYER_ID));
        assertThat(game.getGameId(), equalTo(GAME_ID));
        assertThat(game.getBetAmount(), equalTo(BET));
    }
}