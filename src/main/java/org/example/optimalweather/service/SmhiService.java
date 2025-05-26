package org.example.optimalweather.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.optimalweather.smhi.model.SmhiForecast;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SmhiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SmhiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        this.objectMapper = new ObjectMapper();
    }

    public SmhiForecast getForecast(double lat, double lon) {
        String url = String.format("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json", lon, lat);

        try {
            String json = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(json, SmhiForecast.class);
        } catch (IOException e) {
            throw new RuntimeException("Kunde inte läsa SMHI-data", e);
        }
    }
}
