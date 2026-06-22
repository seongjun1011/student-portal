package com.kopo.portal.model;

/**
 * 대시보드에 표시할 날씨 정보를 담는 간단한 DTO.
 * 외부 API(OpenWeatherMap) 응답을 화면에서 쓰기 쉬운 형태로 가공한 결과를 담는다.
 */
public class Weather {

    private String cityName;
    private double temperature;
    private String description; // 예: "흐리고 비"
    private double windSpeed;

    public Weather() {
    }

    public Weather(String cityName, double temperature, String description, double windSpeed) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
