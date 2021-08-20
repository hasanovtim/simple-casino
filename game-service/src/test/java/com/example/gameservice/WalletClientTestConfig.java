package com.example.gameservice;

import com.example.gameservice.service.WalletClient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class WalletClientTestConfig {
    @Bean
    @Primary
    public WalletClient walletClient() {
        return Mockito.mock(WalletClient.class);
    }
}
