package org.example.optimalweather.controller;


import org.example.optimalweather.model.WeatherForecastDTO;
import org.example.optimalweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherWebController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherWebController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public String showForecast(Model model) {
        WeatherForecastDTO forecast = weatherService.getOptimalForecast();
        model.addAttribute("forecast", forecast);
        return "forecast";
    }
}
