package com.weather.WeatherApp.model;

public class DailyForecast {
    private String date; // Ngày dự báo
    private double tempDay; // Nhiệt độ ban ngày
    private double tempNight; // Nhiệt độ ban đêm
    private String description; // Mô tả thời tiết
    private String icon; // Mã biểu tượng thời tiết

    // Getter và Setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTempDay() {
        return tempDay;
    }

    public void setTempDay(double tempDay) {
        this.tempDay = tempDay;
    }

    public double getTempNight() {
        return tempNight;
    }

    public void setTempNight(double tempNight) {
        this.tempNight = tempNight;
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
}
