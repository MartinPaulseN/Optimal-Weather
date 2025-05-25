package org.example.optimalweather.controller;


import org.example.optimalweather.model.WeatherForecastDTO;
import org.example.optimalweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherRestController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public WeatherForecastDTO getForecast() {
        return weatherService.getOptimalForecast();
    }
}
