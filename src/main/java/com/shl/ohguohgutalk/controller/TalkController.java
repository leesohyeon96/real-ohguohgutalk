package com.shl.ohguohgutalk.controller;

import com.shl.ohguohgutalk.config.WebSocketBrokerConfiguration;
import com.shl.ohguohgutalk.dto.ChatDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // TODO: @reuqestBody, responseBody 안해도 되는 것 필요한지 보고 바꾸던지 하기
@Slf4j
@RequestMapping("/talks")
public class TalkController {
    // private WebSocketBrokerConfiguration template;
    // application 은 brokerChannel 을 통해 메시지 전송할 수 있으며
    // 이때 가장 쉬운 방법은 WebSocketBrokerConfiguration 을 주입받아 메시지를 전송하는 것임
    // 위의 방법은 @SendTo 와 동일한 역할을 하지만
    // - @SendTo는 구독하는 클라언트들에게 메시지 전달
    // - WebSocketBrokerConfiguration 은 명시적으로 원하는 시점에 메시지를 보내는 경우 사용됨
    //   (ex. 특정 이벤트가 발생했을 때 메시지를 보내거나, 특정 동작에 대한 응답으로 메시지 보낼 때 사용)

//    @Autowired
//    public TalkController(WebSocketBrokerConfiguration template) {
//        this.template = template;
//    }

    // 처음 입장 시 호출되는 API
    @MessageMapping("/enter")
    @SendTo("/topic/greetings")
    public ChatDTO greeting(/* 여따가 메시지 담아 보낼 Controller 넣어줘야 함 */) throws Exception {
        log.info("/app/talk : here i am !!");
        return new ChatDTO(ChatDTO.MessageType.ENTER, "roomId", "senderNm", "message Content", "13:00");
    }
}
