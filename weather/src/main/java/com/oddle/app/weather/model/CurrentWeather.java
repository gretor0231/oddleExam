package com.oddle.app.weather.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Weather")
public class CurrentWeather implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="cityName")
    private String cityName;
    @Column(name="description")
    private String description;
    @Column(name="temperature")
    private String temperature;
    @Column(name="savingTime")
    private LocalDateTime savingTime;// we use local time now
    @Column(name="feelsLike")
    private String feelsLike;
    @Column(name="windSpeed")
    private String windSpeed;

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

    public LocalDateTime getSavingTime() {
        return savingTime;
    }

    public void setSavingTime(LocalDateTime savingTime) {
        this.savingTime = savingTime;
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

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "id=" + id +
                ", savingTime=" + savingTime +
                ", description='" + description + '\'' +
                ", temperature='" + temperature + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
