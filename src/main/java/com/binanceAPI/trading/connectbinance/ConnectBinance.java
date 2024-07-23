package com.binanceAPI.trading.connectbinance;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectBinance {
    @Value("${BINANCE_API_KEY}")
    private String binanceApiKey;

    @Value("${BINANCE_API_SECRET}")
    private String binanceApiSecret;

    private static final String serverUrl = "https://api.binance.com";

    public List<KlinesDto> getChartData(String symbol, String interval) {
        try {
            URL url = new URL(serverUrl + "/api/v3/uiKlines?symbol=" + symbol + "&interval=" + interval);
            HttpURLConnection connection = getConnection(url);

            String apiResponse = getResponse(connection);
            apiResponse = apiResponse.replace("[", "").replace("]", "").replace(" ", "");
            List<String> kLines = Arrays.stream(apiResponse.split(",")).collect(Collectors.toList());
            return convertChartData(kLines);

        }
        catch (IOException e) {
            throw new IllegalArgumentException(this + "에서 에러가 발생했습니다.");
        }
    }

    /**
     *
     * @param url
     * @return HttpURLConnection
     * @throws IOException
     *
     * @apiNote
     * api 접근에 공통적인 부분으로 예상됨
     * 추후 다른 클래스로 분리
     */
    private HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        return connection;
    }

    /**
     *
     * @param connection
     * @return String
     * @throws IOException
     * @apiNote 추후 다른 클래스로 분리
     * @see #getConnection(URL)
     */
    private String getResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        String apiResponse = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                apiResponse = response.toString();
            }
        }
        return apiResponse;
    }

    private List<KlinesDto> convertChartData(List<String> kLines) {
        List<KlinesDto> klinesDtoList = new ArrayList<>();
        while (kLines.size() >= 12) {
            List<String> kline = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                kline.add(kLines.remove(0));
            }

            KlinesDto klinesDto = new KlinesDto(kline);
            klinesDtoList.add(klinesDto);
            System.out.println("klinesDto = " + klinesDto);
        }
        return klinesDtoList;
    }
}
