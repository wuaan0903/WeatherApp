package com.weather.WeatherApp.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TimeController {

    private final SimpMessagingTemplate messagingTemplate;

    // Tiêm vào SimpMessagingTemplate để gửi tin nhắn tới các client
    public TimeController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Hàm này sẽ được gọi khi client gửi yêu cầu đến /app/request-time
    @MessageMapping("/request-time")
    public void sendTime(String jsonMessage) {  // Nhận tham số dạng JSON dưới dạng String
        // Giải mã tham số timezone từ JSON
        int timezone = Integer.parseInt(jsonMessage);  // Nếu chỉ gửi timezone dưới dạng chuỗi đơn giản

        Instant nowUTC = Instant.now();
        ZonedDateTime localTime = nowUTC.atZone(ZoneOffset.ofTotalSeconds(timezone));

        // Định dạng thời gian theo dạng HH:mm:ss
        String formattedTime = localTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Gửi thời gian hiện tại tới tất cả các client đăng ký theo topic /topic/time
        messagingTemplate.convertAndSend("/topic/time", formattedTime);
    }
}
