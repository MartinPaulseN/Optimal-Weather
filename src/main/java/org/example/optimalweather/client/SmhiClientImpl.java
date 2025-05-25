package org.example.optimalweather.client;

import org.example.optimalweather.model.WeatherForecastDTO;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

public class SmhiClientImpl implements SmhiClient {

    private final WebClient webclient = WebClient.create();

    @Override
    public WeatherForecastDTO getForecast() {
        String url = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";

        String json = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // TODO: Parsning: hämta temperatur och luftfuktighet ca 24 timmar fram i tiden

        return new WeatherForecastDTO("SMHI", 12.5, 46, LocalDateTime.now());
    }
}
