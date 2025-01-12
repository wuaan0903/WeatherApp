package com.weather.WeatherApp.model;

import java.util.List;

public class Weather {
    private String cityName; // Tên thành phố
    private String country; // Quốc gia
    private String description; // Mô tả thời tiết
    private String icon;
    private double temperature; // Nhiệt độ hiện tại
    private double feelsLike; // Nhiệt độ cảm nhận
    private double tempMin; // Nhiệt độ thấp nhất
    private double tempMax; // Nhiệt độ cao nhất
    private int humidity; // Độ ẩm
    private int pressure; // Áp suất khí quyển
    private double windSpeed; // Tốc độ gió
    private int windDeg; // Hướng gió
    private double windGust; // Gió giật
    private int visibility; // Tầm nhìn (mét)
    private long sunrise; // Giờ mặt trời mọc (UNIX timestamp)
    private long sunset; // Giờ mặt trời lặn (UNIX timestamp)
    private String date; // Thêm thuộc tính ngày
    private List<DailyForecast> forecast; // Dự báo thời tiết nhiều ngày

    // Getter và Setter
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyForecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<DailyForecast> forecast) {
        this.forecast = forecast;
    }
}
