package com.example.gameservice.controller;

import com.example.gameservice.response.GameResponse;
import com.example.gameservice.request.GameRequest;
import com.example.gameservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    private static final String APPLICATION_JSON = "application/json";

    @PostMapping(path = "/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<GameResponse> placeBet(@PathVariable("id") String gameId,
                                                 @Valid @RequestBody GameRequest request) {
        return ResponseEntity.ok(gameService.placeBet(gameId, request));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<List<GameResponse>> betsByGame(@PathVariable("id") String gameId) {

        return ResponseEntity.ok(gameService.getBets(gameId));
    }

    @GetMapping(path = "/", produces = APPLICATION_JSON)
    public ResponseEntity<List<GameResponse>> allBets() {
        return ResponseEntity.ok(gameService.allBets());
    }
}
