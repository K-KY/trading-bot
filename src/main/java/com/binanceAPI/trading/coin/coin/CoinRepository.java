package com.binanceAPI.trading.coin.coin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
}
