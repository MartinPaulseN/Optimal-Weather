package org.example.optimalweather.controller;


import org.example.optimalweather.service.SmhiService;
import org.example.optimalweather.smhi.model.SmhiForecast;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final SmhiService smhiService;

    public WeatherController(SmhiService smhiService) {
        this.smhiService = smhiService;
    }

    @GetMapping
    public SmhiForecast getForecast() {
        return smhiService.getForecast(59.3110, 18.0300);
    }
}
