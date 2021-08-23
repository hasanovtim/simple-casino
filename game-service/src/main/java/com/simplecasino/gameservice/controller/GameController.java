package com.simplecasino.gameservice.controller;

import com.simplecasino.gameservice.response.GameResponse;
import com.simplecasino.gameservice.request.GameRequest;
import com.simplecasino.gameservice.service.GameService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final DozerBeanMapper mapper;

    private static final String APPLICATION_JSON = "application/json";

    @PostMapping(path = "/{id}", produces = APPLICATION_JSON)
    public GameResponse placeBet(@PathVariable("id") String gameId,
                                 @RequestBody GameRequest request) {
        return mapper.map(gameService.placeBet(gameId, request), GameResponse.class);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON)
    public List<GameResponse> betsByGame(@PathVariable("id") String gameId) {

        return gameService.getBets(gameId)
            .stream().map(g -> mapper.map(g, GameResponse.class)).collect(
            Collectors.toList());
    }

    @GetMapping(path = "/", produces = APPLICATION_JSON)
    public List<GameResponse> allBets() {
        return gameService.getAllBets()
            .stream().map(g -> mapper.map(g, GameResponse.class)).collect(
            Collectors.toList());
    }
}
