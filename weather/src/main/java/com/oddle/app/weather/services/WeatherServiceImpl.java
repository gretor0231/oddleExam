package com.oddle.app.weather.services;

import com.oddle.app.weather.Repository.WeatherRepository;
import com.oddle.app.weather.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService{

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }



    @Override
    public CurrentWeather findByCity(String cityName) {
        String websiteResponse = "http://api.openweathermap.org/data/2.5/weather?q="+ cityName + "&appid="+"ccf308f3c554535b877a44c77cf27e64";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(websiteResponse, String.class);
//        System.out.println("the result is :" + result);
        String description = "description";
        String temp = "temp";
        String feelsLike = "feels_like";
        String windSpeed = "speed";
        CurrentWeather currentWeather = new CurrentWeather();
        description = getString(result,description,3);
        temp = getString(result,temp,2);
        feelsLike = getString(result, feelsLike,2);
        windSpeed = getString(result, windSpeed,2);

        currentWeather.setDescription(description);
        currentWeather.setTemperature(temp);
        currentWeather.setCityName(cityName);
        currentWeather.setWindSpeed(windSpeed);
        currentWeather.setFeelsLike(feelsLike);
        return currentWeather;
    }
    @Override
    public void save(String cityName) {
        CurrentWeather currentWeather = findByCity(cityName);
        LocalDateTime savingTime = LocalDateTime.now();
        currentWeather.setSavingTime(savingTime);
        weatherRepository.save(currentWeather);
    }

    @Override
    public List<CurrentWeather> findAllWeather() {
        return weatherRepository.findAll();
    }

    @Override
    public Boolean update(String id) {
        if(!weatherRepository.findById(Long.valueOf(id)).isPresent()){
            return false;
        }
        Optional<CurrentWeather> currentWeather = weatherRepository.findById(Long.valueOf(id));
        String cityName = currentWeather.get().getCityName();
        deleteByID(id);
        save(cityName);
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

    private String getString(String result, String input, Integer number){
        int inputIndex = result.indexOf(input) + input.length() + number;
        String subString = result.substring(inputIndex);
        for (int i = 0; i < subString.length(); i++) {
            if(subString.charAt(i) == ',') {
                subString = subString.substring(0,i);
                input = subString;
                break;
            }else if(subString.charAt(i) == '"') {
                subString = subString.substring(0,i);
                input = subString;
                break;
            }
        }
        return input;
    }
}
