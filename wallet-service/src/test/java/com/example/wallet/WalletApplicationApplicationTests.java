package com.example.wallet;

import com.example.wallet.model.WalletEntity;
import com.example.wallet.request.WalletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class WalletApplicationApplicationTests {
	private static final String PLAYER_ID = "1";
	private static final String WALLET_URL = "/wallet/" + PLAYER_ID;
	private static final String DEPOSIT_URL = WALLET_URL + "/deposit";
	private static final String WITHDRAW_URL = WALLET_URL + "/withdraw";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldRegisterWallet() {
		WalletEntity body = register().getBody();
		assertThat(body.getBalance(), equalTo(new BigDecimal("0")));
		assertThat(body.getPlayerId(), equalTo(PLAYER_ID));
	}

	@Test
	public void shouldReturnErrorOnAttemptToRegisterExistPlayer() {
		register();
		ResponseEntity<WalletEntity> response = register();
		assertThat(response.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@Test
	public void shouldDeposit() {
		//register
		register();
		//deposit
		WalletEntity body = restTemplate.postForEntity(DEPOSIT_URL ,
				new WalletRequest(new BigDecimal("10.50")), WalletEntity.class).getBody();
		assertThat(body.getBalance(), equalTo(new BigDecimal("10.50")));
		assertThat(body.getPlayerId(), equalTo(PLAYER_ID));
	}

	@Test
	public void shouldWithDraw() {
		register();
		//deposit
		restTemplate.postForEntity(DEPOSIT_URL ,
				new WalletRequest(new BigDecimal("10.50")), WalletEntity.class);
		//withdraw
		WalletEntity body = restTemplate.postForEntity(WITHDRAW_URL ,
				new WalletRequest(new BigDecimal("3.30")), WalletEntity.class).getBody();
		assertThat(body.getBalance(), equalTo(new BigDecimal("7.20")));
		assertThat(body.getPlayerId(), equalTo(PLAYER_ID));
	}

	private ResponseEntity<WalletEntity> register() {
		return restTemplate.postForEntity(WALLET_URL, null, WalletEntity.class);
	}
}
