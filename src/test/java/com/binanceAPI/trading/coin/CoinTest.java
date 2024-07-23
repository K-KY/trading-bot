package com.binanceAPI.trading.coin;

import com.binanceAPI.trading.coin.coin.Coin;
import com.binanceAPI.trading.coin.coin.CoinRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
public class CoinTest {

    @Autowired
    private CoinRepository coinRepository;

    @Test
    @Commit
    void dataInsertTest() {
        Coin coin = Coin.builder()
                .coinId(9999999)
                .coinName("TESTDATA")
                .build();

        coinRepository.save(coin);
    }

}
