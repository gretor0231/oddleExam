package com.oddle.app.weather.controller;

import com.oddle.app.weather.model.CurrentWeather;
import com.oddle.app.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{cityName}")
    public Map<String, Object> getWeathers(@PathVariable String cityName)  {
        CurrentWeather currentWeather = weatherService.findByCity(cityName);
        return Collections.singletonMap("message", "the weather of " + cityName +" is : " + currentWeather.toString());
    }
    @GetMapping("/list")
    public Map<String, Object> getAllWeathers() {
        List<CurrentWeather> listOfCurrentWeather = weatherService.findAllWeather();
        return Collections.singletonMap("message", "the all weather information's are : " + listOfCurrentWeather);
    }
    @PostMapping("/{cityName}")
    public Map<String, Object> postWeathers(@PathVariable String cityName) {
        weatherService.save(cityName);
        return Collections.singletonMap("message", "the weather of " + cityName +" already saved. ");
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteWeathers(@PathVariable String id) {
        return Collections.singletonMap("message", "the id already deleted : " + weatherService.deleteByID(id));
    }

    @PutMapping("/{id}")
    public Map<String, Object> putWeathers(@PathVariable String id) {
         return Collections.singletonMap("message", "the id already updated : " + weatherService.update(id));
    }

}