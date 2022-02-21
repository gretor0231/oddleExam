package com.oddle.app.weather.Repository;

import com.oddle.app.weather.model.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;


//connect with mysql database

public interface WeatherRepository extends JpaRepository<CurrentWeather, Long> {

}