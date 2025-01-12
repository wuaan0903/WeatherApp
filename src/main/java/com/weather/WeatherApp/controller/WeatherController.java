package com.weather.WeatherApp.controller;
import com.weather.WeatherApp.model.Weather;
import com.weather.WeatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/current/{city}")
    public Weather getWeather(@PathVariable String city) {
        return weatherService.getCurrentWeather(city);
    }
    @GetMapping("/forecast/{city}")
    public List<Weather> getForecast(@PathVariable String city) {
        return weatherService.getDailyForecast(city);
    }
    @GetMapping("/forecastHour/{city}")
    public List<Weather> getHourlyForecast(@PathVariable String city) {
        return weatherService.getHourlyForecast(city,2,4);
    }
}