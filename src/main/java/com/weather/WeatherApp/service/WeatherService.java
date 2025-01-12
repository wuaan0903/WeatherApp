package com.weather.WeatherApp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weather.WeatherApp.model.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WeatherService {
    private static final String API_KEY = "42274cf3ffc9d7303cb6e3869531b53d";
    private static final String CURRENT_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    // Lấy thời tiết hiện tại
    public Weather getCurrentWeather(String cityName) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", CURRENT_BASE_URL, cityName, API_KEY);

        System.out.println("Current Weather API URL: " + url);
        RestTemplate restTemplate = new RestTemplate();
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        Weather weather = new Weather();
        weather.setCityName(response.get("name").asText());
        weather.setDescription(response.get("weather").get(0).get("description").asText());
        weather.setIcon(response.get("weather").get(0).get("icon").asText());
        weather.setTemperature(response.get("main").get("temp").asDouble());
        weather.setFeelsLike(response.get("main").get("feels_like").asDouble());
        weather.setTempMin(response.get("main").get("temp_min").asDouble());
        weather.setTempMax(response.get("main").get("temp_max").asDouble());
        weather.setHumidity(response.get("main").get("humidity").asInt());
        weather.setPressure(response.get("main").get("pressure").asInt());
        weather.setWindSpeed(response.get("wind").get("speed").asDouble());
        weather.setWindDeg(response.get("wind").get("deg").asInt());
        if (response.get("wind").has("gust")) {
            weather.setWindGust(response.get("wind").get("gust").asDouble());
        }
        if (response.has("visibility")) {
            weather.setVisibility(response.get("visibility").asInt());
        }
        return weather;
    }

    // Lấy dự báo thời tiết theo ngày
    public List<Weather> getDailyForecast(String cityName) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", FORECAST_BASE_URL, cityName, API_KEY);

        System.out.println("Forecast API URL: " + url);
        RestTemplate restTemplate = new RestTemplate();
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        JsonNode list = response.get("list");
        Map<String, List<JsonNode>> groupedByDay = new LinkedHashMap<>();

        // Nhóm các bản ghi dự báo theo ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (JsonNode node : list) {
            String date = node.get("dt_txt").asText().split(" ")[0];
            groupedByDay.computeIfAbsent(date, k -> new ArrayList<>()).add(node);
        }

        // Tạo danh sách Weather theo ngày
        List<Weather> dailyForecast = new ArrayList<>();
        for (Map.Entry<String, List<JsonNode>> entry : groupedByDay.entrySet()) {
            String date = entry.getKey();
            List<JsonNode> records = entry.getValue();

            // Tìm nhiệt độ thấp nhất và cao nhất trong ngày
            double minTemp = Double.MAX_VALUE;
            double maxTemp = Double.MIN_VALUE;

            for (JsonNode record : records) {
                double tempMin = record.get("main").get("temp_min").asDouble();
                double tempMax = record.get("main").get("temp_max").asDouble();

                if (tempMin < minTemp) {
                    minTemp = tempMin;
                }

                if (tempMax > maxTemp) {
                    maxTemp = tempMax;
                }
            }

            // Lấy bản ghi đầu tiên làm đại diện và cập nhật nhiệt độ
            JsonNode firstRecord = records.get(0);

            Weather weather = mapToWeather(firstRecord);
            weather.setDate(date); // Đặt ngày
            weather.setTempMin(minTemp); // Đặt nhiệt độ thấp nhất
            weather.setTempMax(maxTemp); // Đặt nhiệt độ cao nhất
            dailyForecast.add(weather);
        }

        return dailyForecast;
    }

    public List<Weather> getHourlyForecast(String cityName, int startIndex, int recordCount) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", FORECAST_BASE_URL, cityName, API_KEY);

        System.out.println("Forecast API URL: " + url);
        RestTemplate restTemplate = new RestTemplate();
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        JsonNode list = response.get("list");
        List<Weather> hourlyForecast = new ArrayList<>();

        // Kiểm tra startIndex hợp lệ
        if (startIndex >= list.size() || startIndex < 0) {
            throw new IllegalArgumentException("startIndex không hợp lệ!");
        }

        // Lấy bản ghi từ startIndex, giới hạn số lượng là recordCount
        for (int i = startIndex; i < Math.min(startIndex + recordCount, list.size()); i++) {
            JsonNode record = list.get(i);
            Weather weather = mapToWeather(record);
            String hourMinute = record.get("dt_txt").asText().split(" ")[1].substring(0, 5); // "18:00"
            weather.setDate(hourMinute);
            hourlyForecast.add(weather);
        }

        return hourlyForecast;
    }


    // Hàm ánh xạ JSON thành đối tượng Weather
    private Weather mapToWeather(JsonNode node) {
        Weather weather = new Weather();
        weather.setDescription(node.get("weather").get(0).get("description").asText());
        weather.setIcon(node.get("weather").get(0).get("icon").asText());
        weather.setTemperature(node.get("main").get("temp").asDouble());
        weather.setFeelsLike(node.get("main").get("feels_like").asDouble());
        weather.setTempMin(node.get("main").get("temp_min").asDouble());
        weather.setTempMax(node.get("main").get("temp_max").asDouble());
        weather.setHumidity(node.get("main").get("humidity").asInt());
        weather.setPressure(node.get("main").get("pressure").asInt());
        weather.setWindSpeed(node.get("wind").get("speed").asDouble());
        weather.setWindDeg(node.get("wind").get("deg").asInt());
        if (node.get("wind").has("gust")) {
            weather.setWindGust(node.get("wind").get("gust").asDouble());
        }
        if (node.has("visibility")) {
            weather.setVisibility(node.get("visibility").asInt());
        }
        return weather;
    }
}
