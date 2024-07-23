package com.binanceAPI.trading.coin.coin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "coin")
public class Coin {
    @Id
    @Column(name = "coin_id")
    private int coinId;

    @Column(name = "coin_name")
    private String coinName;
}




