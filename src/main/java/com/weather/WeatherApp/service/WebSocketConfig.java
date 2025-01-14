package com.weather.WeatherApp.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Cấu hình message broker để gửi thông điệp đến các client
        config.enableSimpleBroker("/topic");  // Các message gửi đến topic sẽ được xử lý
        config.setApplicationDestinationPrefixes("/app"); // Đặt prefix cho các endpoint gửi yêu cầu
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Đăng ký điểm cuối cho WebSocket, nơi client có thể kết nối
        registry.addEndpoint("/time-websocket").withSockJS();  // Địa chỉ điểm cuối WebSocket
    }
}
