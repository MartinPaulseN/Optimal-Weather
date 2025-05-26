package org.example.optimalweather.service;

import org.example.optimalweather.model.WeatherForecastDTO;

public interface WeatherService {
    WeatherForecastDTO getOptimalForecast(double lat, double lon);
}
