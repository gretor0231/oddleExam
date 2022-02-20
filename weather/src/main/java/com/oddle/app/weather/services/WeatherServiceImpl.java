package com.oddle.app.weather.services;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.oddle.app.weather.Repository.WeatherRepository;
import com.oddle.app.weather.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class WeatherServiceImpl implements WeatherService{

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public CurrentWeather findByCity(String cityName) {
        String websiteResponse = "http://api.openweathermap.org/data/2.5/weather?q="+ cityName + "&mode=json&appid="+"ccf308f3c554535b877a44c77cf27e64"+"&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(websiteResponse, String.class);
        String description = null;
        String temp;
        String feelsLike;
        String windSpeed;
        CurrentWeather currentWeather = new CurrentWeather();
        JSONObject root = new JSONObject(Boolean.parseBoolean(result));
        JSONArray weatherObject = root.getJSONArray("weather");
        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject elementInArray = weatherObject.getJSONObject(i);
            description = elementInArray.getString("description");
        }
        JSONObject main = root.getJSONObject("main");
        temp = String.valueOf(main.getFloat("temp"));
        feelsLike = String.valueOf(main.getFloat("feelsLike"));
        windSpeed = String.valueOf(main.getFloat("windSpeed"));
        currentWeather.setCityName(cityName);
        currentWeather.setDescription(description);
        currentWeather.setTemperature(temp);
        currentWeather.setFeelsLike(feelsLike);
        currentWeather.setWindSpeed(windSpeed);
        return currentWeather;
    }
    @Override
    public void save(String cityName) {
        CurrentWeather currentWeather = findByCity(cityName);
        weatherRepository.save(currentWeather);
    }

    @Override
    public List<CurrentWeather> findAllWeather() {
        return weatherRepository.findAll();
    }

    @Override
    public Boolean update(CurrentWeather currentWeather) {
        if(!weatherRepository.findById(currentWeather.getId()).isPresent()){
            return false;
        }
        weatherRepository.save(currentWeather);
        return true;
    }
    @Override
    public Boolean deleteByID(String id) {
        if(!weatherRepository.findById(Long.valueOf(id)).isPresent()){
            return false;
        }
        weatherRepository.deleteById(Long.valueOf(id));
        return true;
    }
}
