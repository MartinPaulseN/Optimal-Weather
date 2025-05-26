package org.example.optimalweather.service;


import org.example.optimalweather.model.WeatherForecastDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SmhiWeatherSourceTest {

    @Autowired
    private SmhiWeatherSource smhiWeatherSource;

    @Test
    public void testGetForecast_returnsValidData() {
        double lat = 59.3110;
        double lon = 18.0300;

        WeatherForecastDTO forecastDTO = smhiWeatherSource.getForecast(lat, lon);

        assertNotNull(forecastDTO, "Forecast should not be null");
        assertEquals("SMHI", forecastDTO.getOrigin());
        assertTrue(forecastDTO.getTemperature() > -50 && forecastDTO.getTemperature() < 60, "Temperature should be in a reasonable range");
        assertTrue(forecastDTO.getHumidity() >= 0 && forecastDTO.getHumidity() <= 100, "Humidity should be between 0 and 100");
        assertNotNull(forecastDTO.getTimestamp(), "Timestamp should not be null");

        System.out.println("Weather forecast from SMHI: " + forecastDTO.getTemperature() + "C, humidity: " + forecastDTO.getHumidity() + "% at " + forecastDTO.getTimestamp());
    }
}
