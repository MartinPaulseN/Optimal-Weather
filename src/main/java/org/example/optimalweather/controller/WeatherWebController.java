package org.example.optimalweather.controller;


import org.example.optimalweather.model.WeatherForecastDTO;
import org.example.optimalweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherWebController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherWebController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public String showForecast(
            @RequestParam(defaultValue = "59.3110") double lat,
            @RequestParam(defaultValue = "18.0300") double lon,
            Model model) {
        WeatherForecastDTO forecast = weatherService.getOptimalForecast(lat, lon);
        model.addAttribute("forecast", forecast);
        System.out.println("Forecast: " + forecast);
        return "forecast";
    }
}
