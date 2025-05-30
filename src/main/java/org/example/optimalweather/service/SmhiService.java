package org.example.optimalweather.service;


import org.example.optimalweather.smhi.model.SmhiForecast;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class SmhiService {

    private final WebClient webClient;

    public SmhiService() {
        this.webClient = WebClient.create();
    }

    public SmhiForecast getForecast(double lat, double lon) {
        String url = String.format(
                "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json",
                lon, lat
        );

        Mono<SmhiForecast> forecastMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(SmhiForecast.class);

        return forecastMono.block();
    }
}
