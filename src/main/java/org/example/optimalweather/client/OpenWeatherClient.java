package org.example.optimalweather.client;

import org.example.optimalweather.model.WeatherForecastDTO;

public interface OpenWeatherClient {
    weatherForecastDTO getForecast();
}
