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

//    get the weather information from openWeather by cityName
//    the Url example would be http://localhost:8080/weather/shanghai
    @GetMapping("/{cityName}")
    public Map<String, Object> getWeathers(@PathVariable String cityName)  {
        CurrentWeather currentWeather = weatherService.findByCity(cityName);
        return Collections.singletonMap("message", "the weather of " + cityName +" is : " + currentWeather.toString());
    }
//    get the weather information from mySql database
//    the Url example would be http://localhost:8080/weather/list
    @GetMapping("/list")
    public Map<String, Object> getAllWeathers() {
        List<CurrentWeather> listOfCurrentWeather = weatherService.findAllWeather();
        return Collections.singletonMap("message", "the all weather information's are : " + listOfCurrentWeather);
    }
//    save the weather information from openWeather by cityName
//    the Url example would be http://localhost:8080/weather/shanghai
    @PostMapping("/{cityName}")
    public Map<String, Object> postWeathers(@PathVariable String cityName) {
        weatherService.save(cityName);
        return Collections.singletonMap("message", "the weather of " + cityName +" already saved. ");
    }
//    delete the weather information from mySql database by id
//    the Url example would be http://localhost:8080/weather/1
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteWeathers(@PathVariable String id) {
        return Collections.singletonMap("message", "the id already deleted : " + weatherService.deleteByID(id));
    }
//    update the weather information from mySql database by id
//    the Url example would be http://localhost:8080/weather/1
    @PutMapping("/{id}")
    public Map<String, Object> putWeathers(@PathVariable String id) {
         return Collections.singletonMap("message", "the id already updated : " + weatherService.update(id));
    }

}