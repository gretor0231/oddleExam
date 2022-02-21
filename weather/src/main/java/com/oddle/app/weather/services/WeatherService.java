package com.oddle.app.weather.services;

import com.oddle.app.weather.model.CurrentWeather;

import java.util.List;

public interface WeatherService {

    CurrentWeather findByCity(String cityName);// find the most recent weather info by cityname

    void save(String cityName);// saving current weather date to mysql database

    List<CurrentWeather> findAllWeather();// find past periods weather data

    Boolean update(String id);// update past period weather data

    Boolean deleteByID(String id);// delete past period weather data

}
