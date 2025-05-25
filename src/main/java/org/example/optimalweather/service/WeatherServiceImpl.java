package org.example.optimalweather.service;


import org.example.optimalweather.client.OpenWeatherClient;
import org.example.optimalweather.client.SmhiClient;
import org.example.optimalweather.model.WeatherForecastDTO;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService{

    private final OpenWeatherClient openWeatherClient;
    private final SmhiClient smhiClient;

    public WeatherServiceImpl(OpenWeatherClient openWeatherClient, SmhiClient smhiClient) {
        this.openWeatherClient = openWeatherClient;
        this.smhiClient = smhiClient;
    }

    @Override
    public WeatherForecastDTO getOptimalForecast() {
        WeatherForecastDTO ow = openWeatherClient.getForecast();
        WeatherForecastDTO smhi = smhiClient.getForecast();

        return ow.getTemperature() > smhi.getTemperature() ? ow : smhi;
    }
}
