package org.example.optimalweather.mapper;

import jakarta.annotation.Nullable;
import org.example.optimalweather.model.WeatherForecastDTO;
import org.example.optimalweather.smhi.model.SmhiForecast;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Component
public class SmhiMapper {

    @Nullable
    public WeatherForecastDTO MapToDTO(SmhiForecast forecast) {
        if (forecast == null || forecast.getTimeSeries() == null || forecast.getTimeSeries().isEmpty()) {
            return null;
        }

        var firstTimeSeries = forecast.getTimeSeries().get(0);

        Double temperature = null;
        Integer humidity = null;

        for (var param : firstTimeSeries.getParameters()) {
            if ("t".equals(param.getName()) && param.getValues() != null && !param.getValues().isEmpty()) {
                temperature = param.getValues().get(0).doubleValue();
            } else if ("r".equals(param.getName()) && param.getValues() != null && !param.getValues().isEmpty()) {
                humidity = param.getValues().get(0).intValue();
            }
        }

        LocalDateTime timestamp = null;
        try {
            timestamp = ZonedDateTime.parse(firstTimeSeries.getValidTime()).toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (temperature != null && humidity != null && timestamp != null) {
            return new WeatherForecastDTO("SMHI", temperature, humidity, timestamp);
        } else {
            return null;
        }
    }
}
