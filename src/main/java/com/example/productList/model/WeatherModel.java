package com.example.productList.model;

import java.util.List;

public class WeatherModel {
    private String name;
    private String region;
    private String country;
    private String local_time;
    private double temp_c;
    private String condition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocal_time() {
        return local_time;
    }

    public void setLocal_time(String local_time) {
        this.local_time = local_time;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getFeelsLike_c() {
        return feelsLike_c;
    }

    public void setFeelsLike_c(double feelsLike_c) {
        this.feelsLike_c = feelsLike_c;
    }

    private double humidity;
    private double feelsLike_c;
}
