package org.example.optimalweather.model;

import java.time.LocalDateTime;

public class WeatherForecastDTO {
    private String origin;
    private double temperature;
    private int humidity;
    private LocalDateTime timestamp;

    public WeatherForecastDTO(String origin, double temperature, int humidity, LocalDateTime timestamp) {
        this.origin = origin;
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
