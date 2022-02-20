package com.oddle.app.weather.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name="Weather")
public class CurrentWeather implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private LocalTime savingTime;// we use local time now
    @Column(name="description")
    private String description;
    @Column(name="temperature")
    private String temperature;
    @Column(name="feelsLike")
    private String feelsLike;
    @Column(name="windSpeed")
    private String windSpeed;
    @Column(name="cityName")
    private String cityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LocalTime getSavingTime() {
        return savingTime;
    }

    public void setSavingTime() {
        this.savingTime = LocalTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
