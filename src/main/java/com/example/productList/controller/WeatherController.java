package com.example.productList.controller;

import com.example.productList.model.WeatherModel;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {


    @GetMapping("/weather")
    public  String weather(@RequestParam("city") String city, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://api.weatherapi.com/v1/current.json?key=21caac3ce41e4a2ca9d105147222903&q=" + city + "&aqi=no";
        ResponseEntity<String> response
                = restTemplate.getForEntity(ResourceUrl + "", String.class);

        WeatherModel weatherModel = new WeatherModel();
        JSONObject jsonObject = new JSONObject(response.getBody());
        String name = jsonObject.getJSONObject("location").get("name").toString();
        weatherModel.setName(name);
        String region = jsonObject.getJSONObject("location").get("region").toString();
        weatherModel.setRegion(region);
        String country = jsonObject.getJSONObject("location").get("country").toString();
        weatherModel.setCountry(country);
        String temp_c = jsonObject.getJSONObject("current").get("temp_c").toString();
        double temp = Double.parseDouble(temp_c);
        weatherModel.setTemp_c(temp);
        String condition = jsonObject.getJSONObject("current").getJSONObject("condition").get("text").toString();

        weatherModel.setCondition(condition);
        model.addAttribute("weather", weatherModel);

        return "weather";

    }



}
