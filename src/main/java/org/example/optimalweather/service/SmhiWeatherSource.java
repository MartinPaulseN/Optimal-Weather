package org.example.optimalweather.service;

import org.example.optimalweather.mapper.SmhiMapper;
import org.example.optimalweather.model.WeatherForecastDTO;
import org.example.optimalweather.smhi.model.SmhiForecast;
import org.springframework.stereotype.Component;

@Component
public class SmhiWeatherSource implements WeatherSource{

    private final SmhiService smhiService;
    private final SmhiMapper smhiMapper;

    public SmhiWeatherSource(SmhiService smhiService, SmhiMapper smhiMapper) {
        this.smhiMapper = smhiMapper;
        this.smhiService = smhiService;
    }

    @Override
    public WeatherForecastDTO getForecast(double lat, double lon) {
        SmhiForecast smhiForecast = smhiService.getForecast(lat, lon);
        return smhiMapper.MapToDTO(smhiForecast);
    }
}
