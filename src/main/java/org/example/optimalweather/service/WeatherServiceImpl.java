package org.example.optimalweather.service;


import org.example.optimalweather.client.OpenWeatherClient;
import org.example.optimalweather.client.SmhiClient;
import org.example.optimalweather.model.WeatherForecastDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final OpenWeatherClient openWeatherClient;
    private final SmhiClient smhiClient;

    public WeatherServiceImpl(OpenWeatherClient openWeatherClient, SmhiClient smhiClient) {
        this.openWeatherClient = openWeatherClient;
        this.smhiClient = smhiClient;
    }

    @Override
    public WeatherForecastDTO getOptimalForecast(double lat, double lon) {
        WeatherForecastDTO ow = openWeatherClient.getForecast(lat, lon);
        WeatherForecastDTO smhi = smhiClient.getForecast(lat, lon);

        if (ow != null && smhi != null) {
            return ow.getTemperature() > smhi.getTemperature() ? ow : smhi;
        } else if (ow != null) {
            return ow;
        } else if (smhi != null) {
            return smhi;
        } else {
            return new WeatherForecastDTO("Ingen data", 0.0, 0, LocalDateTime.now());
        }
    }
}
