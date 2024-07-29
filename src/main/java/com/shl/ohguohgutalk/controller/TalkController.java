package com.shl.ohguohgutalk.controller;

import com.shl.ohguohgutalk.dto.ChatDTO;
import com.shl.ohguohgutalk.entity.Chat;
import com.shl.ohguohgutalk.service.TalkService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController // TODO: @reuqestBody, responseBody 안해도 되는 것 필요한지 보고 바꾸던지 하기
@Slf4j
@RequestMapping("/talks")
public class TalkController {
    private final ModelMapper modelMapper;
    private final TalkService talkService;

    @Autowired
    public TalkController(ModelMapper modelMapper, TalkService talkService) {
        this.modelMapper = modelMapper;
        this.talkService = talkService;
    }

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
    @MessageMapping("talk")
    @SendTo("/topic/greetings")
    public ChatDTO greeting(/* 여따가 메시지 담아 보낼 Controller 넣어줘야 함 */String message) {
        log.info("/app/talk : message : {}", message);

        // mongoDB에 해당 채팅 저장
        Chat chat = Chat.of(message, LocalDateTime.now());
        talkService.saveChats(chat);
        // 저장하고 해당 내용 반환? 해서 보여주기
        DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        return new ChatDTO(ChatDTO.MessageType.ENTER, "roomId", "누군가 ", message, chat.getLocalDateTime().format(yyyyMMddHHmm));
    }

    /* @MessageMapping, @SendTo, @SendToUser
       1. @SendTo vs @SendToUser
       : @SendTo는 1:n 으로 메시지를 뿌릴 때 사용하는 구조 | 보통 경로가 /topic 으로 시작함
       : @SendToUser 는 특정 사용자를 대상으로 메시지를 뿌릴 때 사용하는 구조 | 보통 경로가 /queue 로 시작함

       2. @MessageMapping
       : 특정 사용자가 /enter 라는 경로로 메시지를 보내면 /topic/greetings 이라는 topic 을 구독하고 있는 사용자들에게 메시지 전달됨
       : 메시지를 보내는 html 에서 /app/talk 으로 send 하는 경우 configuration 에 의해 /app 은 무시되고
       : @MessageMapping("talk")을 가진 메소드를 찾아 도달함 !
     */

    @GetMapping("/")
    public List<ChatDTO> getTalkList() {
        List<Chat> talkList = talkService.getTalkList();

        // domain -> dto 변환 후 반환
        modelMapper.typeMap(Chat.class, ChatDTO.class).addMappings(mapper -> {
            mapper.using((Converter<LocalDateTime, String>) context -> context.getSource().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                    .map(Chat::getLocalDateTime, ChatDTO::setTime);
        });

        return talkList.stream()
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .collect(Collectors.toList());
    }



}
