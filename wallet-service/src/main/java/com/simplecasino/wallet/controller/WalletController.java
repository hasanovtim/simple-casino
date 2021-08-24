package com.simplecasino.wallet.controller;

import com.simplecasino.wallet.request.WalletRequest;
import com.simplecasino.wallet.response.WalletResponse;
import com.simplecasino.wallet.service.WalletService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;
    private static final String APPLICATION_JSON = "application/json";
    private final DozerBeanMapper mapper;

    @PostMapping(path = "/{id}", produces = APPLICATION_JSON)
    public WalletResponse registerWallet(@PathVariable("id") @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$") String playerId) {
        return mapper.map(walletService.registerWallet(playerId), WalletResponse.class);
    }

    @GetMapping(path = "/", produces = APPLICATION_JSON)
    public List<WalletResponse> allWallets() {
        return walletService.getAllWallets()
            .stream().map(wallet -> mapper.map(wallet, WalletResponse.class))
            .collect(Collectors.toList());
    }


    @GetMapping(path = "/{id}", produces = APPLICATION_JSON)
    public WalletResponse getWallet(@PathVariable("id") String playerId) {
        return mapper.map(walletService.getWallet(playerId), WalletResponse.class);
    }

    @PostMapping(value = "/{id}/deposit", produces = APPLICATION_JSON)
    public WalletResponse deposit(@PathVariable("id") String playerId,
                                                @Valid @RequestBody WalletRequest request) {
        return mapper.map(walletService.deposit(playerId, request.getAmount()), WalletResponse.class);
    }

    @PostMapping(path = "/{id}/withdraw", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public WalletResponse withdraw(@PathVariable("id") String playerId,
                                                 @Valid @RequestBody WalletRequest request) {
        return mapper.map(walletService.withdraw(playerId, request), WalletResponse.class);
    }
}
