package com.binanceAPI.trading.connectbinance;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectBinanceTest {

    private final ConnectBinance connectBinance = new ConnectBinance();

    @Test
    void getChartTest() {
        List<KlinesDto> chartData = connectBinance.getChartData("SANDUSDT", "1h");
        System.out.println("chartData = " + chartData);
        assertThat(chartData.size() > 0).isTrue();
    }
}
