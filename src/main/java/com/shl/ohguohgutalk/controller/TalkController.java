package com.shl.ohguohgutalk.controller;

import com.shl.ohguohgutalk.dto.ChatDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController // TODO: @reuqestBody, responseBody 안해도 되는 것 필요한지 보고 바꾸던지 하기
public class TalkController {

    // 처음 입장 시 호출되는 API
    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public ChatDTO greeting() throws Exception {
        Thread.sleep(1000);
        return null;
    }
}
