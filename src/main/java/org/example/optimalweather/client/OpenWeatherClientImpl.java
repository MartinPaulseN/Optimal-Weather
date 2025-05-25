package org.example.optimalweather.client;

import org.example.optimalweather.model.WeatherForecastDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class OpenWeatherClientImpl implements OpenWeatherClient {

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    public OpenWeatherClientImpl() {
        this.webClient = WebClient.create("https://api.openweathermap.org/data/2.5");
    }

    @Override
    public WeatherForecastDTO getForecast() {
        double lat = 59.3110; //Liljeholmen
        double lon = 18.0300;

        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/forecast")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("units", "metric")
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> forecast = ((java.util.List<Map<String, Object>>) response.get("list")).get(8);
        Map<String, Object> main = (Map<String, Object>) forecast.get("main");

        double temp = ((Number) main.get("temp")).doubleValue();
        int humidity = ((Number) main.get("humidity")).intValue();
        String timeText = (String) forecast.get("dt_text");
        LocalDateTime timestamp = LocalDateTime.parse(timeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return new WeatherForecastDTO("OpenWeatherMap", temp, humidity, timestamp);
    }
}
