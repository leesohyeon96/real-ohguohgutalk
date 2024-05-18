package com.shl.ohguohgutalk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfiguration implements WebSocketMessageBrokerConfigurer {

    // spring 에서 메시지 전달 > Message broker(RabbitMQ 같은 애들)에 전달 > broker 가 sub(구독자들) 관리 및 메시지 broadcasting(전달)
    // 위의 역할을 위한 broker configuration !
    // 이 WebSocketBrokerConfiguration 빈이 등록되면 broker 를 통해 메시지의 ConvertAndSend 가 가능해짐

    // configureMessageBroker : 메시지 브로커를 설정하는 메소드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // enableSimpleBroker : 내장 메시지 브로커 사용
        // -> 내장 메시지 브로커를 통해 Subscriptions, Broadcasting 기능을 제공
        //    /topic 'destination' 헤더를 가진 메시지 브로커를 통해 라우팅 됨
        // /topic : 메시지 구독하는 요청 url
        registry.enableSimpleBroker("/topic");

        // setApplicationDestinationPrefixes : 메시지 핸들러로 라우팅되는 prefix(/app)을 파라미터로 지정할 수 있음
        // -> /app 'destination' 헤더 경로는 @Controller 객체의 @MessageMapping 메소드로 라우팅 됨
        // /app : 메시지를 요청하는 요청 url
        registry.setApplicationDestinationPrefixes("/app");
    }

    // registerStompEndpoints : 처음 웹소켓 핸드쉐이크를 위한 주소
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /ws-connect : stomp 접속 주소 url
        // Endpoint 설정 : Websocket or SockJS Client 의 웹소켓 Handshake 커넥션 생성 경로
        registry.addEndpoint("/ws-connect") // 연결된 EndPoint
//                .setAllowedOrigins("*") // (*) 모든 것 접근 가능하도록 함 -> 그러나 이렇게 하면 cors 에러가 남
                .setAllowedOrigins("http://localhost:8081") // 고로 특정 도메인만 접근할 수 있도록 설정
                .withSockJS(); // SockJS를 연결한다는 설정
    }

    /* [메시지 전반적인 흐름 with STOMP]
       1. client 가 http://localhost:8080/ws-connect URL 을 통해 WebSocket 커넥션 생성 > STOMP 프레임이 진행되기 시작
       -> chat.html 에서 버튼 클릭 시 시작

       2. client 가 destination 헤더가 /topic/connect 인 SUBSCRIBE 프레임을 보냄 > 수신되어 Decode 된 메시지는 clientInboundChannel 을 통해
          메시지 브로커로 라우팅 됨
          *clientInboundChannel : WebSocket Client 로부터 들어오는 요청을 전달 > 즉, client 로부터 받은 메시지 전달
        -> [1에서 버튼 클릭 시 subscribe 저거 보내도록 하고 채팅치면 2번 과정을 할 수 있도록?] -> 이어서 3번 과정으로 채팅 칠때마다 저렇게 보낼 수 잇도록 하기

       3. client 가 destination 헤더가 /app/connect 인 SEND 프레임을 보냄 > /app 접두사는 @Controller 객체로(내가 설정할 Controller ㅇㅇ) 라우팅하며
          남은 /connect 파트는 Controller 내의 @MessageMapping 된 메소드로 라우팅됨
          ex) @MessageMapping("/connect")
              public String handle(String connect) {
                return "[" + getTimestamp() + ": " + connect;
              }

       4. 메소드 return 값은 payload 를 가진 Spring message 로 치환되어 destination 헤더 /topic/connect 을 가짐
          ( /app/connect 에서 유도되어 /app이 디폴트 DestinationPrefix 인 /topic 로 변환됨)
          변환된 메시지는 brokerChannel 을 통해 메시지 브로커에 의해 핸들링됨

       5. 메시지 브로커는 매칭되는 모든 Subscriber 를 찾아 MESSAGE 프레임을 clientOutboundChannel 을 통해 전송함
          메시지는 STOMP 프레임으로 encode 되어 WebSocket 커넥션을 통해 전송됨
         *clientOutboundChannel : WebSocket Client 로 Server 의 메시지를 내보냄 > 즉, client 에게 메시지를 전달함

       [추가로 알아두기]
       *brokerChannel : Server 내부에서 사용하는 채널 > broker 에게 메시지를 전달

       *나머지 설명은 Controller 로 이동 !


       *참고 : https://blog.leaphop.co.kr/blogs/56

     */
}
