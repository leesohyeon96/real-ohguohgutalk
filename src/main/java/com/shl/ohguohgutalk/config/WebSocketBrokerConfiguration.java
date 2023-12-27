package com.shl.ohguohgutalk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfiguration implements WebSocketMessageBrokerConfigurer {

    // configureMessageBroker : 메시지 브로커를 설정하는 메소드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enableSimpleBroker : 내장 메시지 브로커 사용
        // /sub : 메시지 구독하는 요청 url
        registry.enableSimpleBroker("/sub");
        // setApplicationDestinationPrefixes : 메시지 핸들러로 라우팅되는 prefix(/pub)을 파라미터로 지정할 수 있음
        // /pub : 메시지를 요청하는 요청 url
        registry.setApplicationDestinationPrefixes("/pub");
    }

    // registerStompEndpoints : 처음 웹소켓 핸드쉐이크를 위한 주소
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /ws-connect : stomp 접속 주소 url
        registry.addEndpoint("/ws-connect") // 연결된 EndPoint
                .withSockJS(); // SockJS를 연결한다는 설정
    }
}
