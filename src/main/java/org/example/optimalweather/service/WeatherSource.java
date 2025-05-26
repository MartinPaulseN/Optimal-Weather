package org.example.optimalweather.service;

import org.example.optimalweather.model.WeatherForecastDTO;

public interface WeatherSource {
    WeatherForecastDTO getForecast(double lat, double lon);
}
