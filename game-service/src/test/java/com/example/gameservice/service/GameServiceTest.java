package com.example.gameservice.service;

import com.example.gameservice.model.GameEntity;
import com.example.gameservice.repository.GameRepository;
import com.example.gameservice.request.GameRequest;
import com.example.gameservice.request.WalletRequest;
import com.example.gameservice.response.GameResponse;
import com.example.gameservice.response.WalletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameServiceTest {
    private static final String PLAYER_ID = "123";
    private static final String GAME_ID = "5000";
    private static final BigDecimal BALANCE = new BigDecimal("10.00");
    private static final BigDecimal BET = new BigDecimal("3.00");
    private static final BigDecimal BALANCE_AFTER_WITHDRAW = new BigDecimal("7.00");

    @Mock
    private WalletClientWrapper walletClient;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    public void shouldRegisterGame() {
        Mockito.when(walletClient.getWallet(PLAYER_ID)).thenReturn(new WalletResponse(PLAYER_ID, BALANCE));

        Mockito.when(gameRepository.save(new GameEntity(GAME_ID, PLAYER_ID, BET)))
                .thenReturn(new GameEntity(GAME_ID, PLAYER_ID, BALANCE));

        GameResponse response = gameService.placeBet(GAME_ID, new GameRequest(PLAYER_ID, BET));
        assertThat(response.getPlayerId(), equalTo(PLAYER_ID));
        assertThat(response.getGameId(), equalTo(GAME_ID));
        assertThat(response.getBetAmount(), equalTo(BET));
        verify(gameRepository).save(new GameEntity(GAME_ID, PLAYER_ID, BET));
    }
}