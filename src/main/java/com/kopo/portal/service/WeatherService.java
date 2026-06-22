package com.kopo.portal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kopo.portal.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 외부 날씨 API(OpenWeatherMap)를 연동하는 서비스.
 * application.properties 의 weather.api.key 값이 비어있거나
 * API 호출이 실패하면, 화면이 비어보이지 않도록 더미 데이터를 반환한다.
 */
@Service
public class WeatherService {

    @Value("${weather.api.key:}")
    private String apiKey;

    @Value("${weather.api.city:Seoul}")
    private String city;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Weather getCurrentWeather() {
        if (apiKey == null || apiKey.isBlank()) {
            return dummyWeather();
        }
        try {
            String url = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=kr",
                    city, apiKey);
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            double temp = root.path("main").path("temp").asDouble();
            String description = root.path("weather").get(0).path("description").asText();
            double wind = root.path("wind").path("speed").asDouble();
            return new Weather(city, temp, description, wind);
        } catch (Exception e) {
            // API 키 미발급, 네트워크 오류 등 어떤 이유로든 실패하면 더미 데이터로 대체
            return dummyWeather();
        }
    }

    private Weather dummyWeather() {
        return new Weather(city, 23.0, "흐리고 비 (더미 데이터, API 키 미설정)", 2.2);
    }
}
