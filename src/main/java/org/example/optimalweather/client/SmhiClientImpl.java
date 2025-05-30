package org.example.optimalweather.client;

import org.example.optimalweather.model.WeatherForecastDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Component
public class SmhiClientImpl implements SmhiClient {

    private final WebClient webClient = WebClient.create();

    @Override
    public WeatherForecastDTO getForecast(double lat, double lon) {
        String url = String.format("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/%.4f/lat/%.4f/data.json",
                lon, lat
        );

        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || !response.containsKey("timeSeries")) {
            return null;
        }

        List<Map<String, Object>> timeSeries = (List<Map<String, Object>>) response.get("timeSeries");

        // Mål: prognos ca 24 timmar framåt
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime targetTime = now.plusHours(24);

        Map<String, Object> closestForecast = null;
        Duration smallestDiff = Duration.ofDays(10); // stort initialt värde

        for (Map<String, Object> forecast : timeSeries) {
            String validTimeStr = (String) forecast.get("validTime");
            ZonedDateTime forecastTime = ZonedDateTime.parse(validTimeStr);

            Duration diff = Duration.between(targetTime, forecastTime).abs();
            if (diff.compareTo(smallestDiff) < 0) {
                smallestDiff = diff;
                closestForecast = forecast;
            }
        }

        if (closestForecast == null) {
            return null;
        }

        List<Map<String, Object>> parameters = (List<Map<String, Object>>) closestForecast.get("parameters");

        Double temperature = null;
        Integer humidity = null;

        for (Map<String, Object> param : parameters) {
            String name = (String) param.get("name");
            List<Object> values = (List<Object>) param.get("values");

            if (name.equals("t")) {
                temperature = ((Number) values.get(0)).doubleValue();
            } else if (name.equals("r")) {
                humidity = ((Number) values.get(0)).intValue();
            }
        }

        if (temperature == null || humidity == null) {
            return null;
        }

        ZonedDateTime validTime = ZonedDateTime.parse((String) closestForecast.get("validTime"));
        LocalDateTime timestamp = validTime.toLocalDateTime();

        return new WeatherForecastDTO("SMHI", temperature, humidity, timestamp);
    }
}
