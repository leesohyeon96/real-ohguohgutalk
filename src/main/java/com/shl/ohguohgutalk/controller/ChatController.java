package com.shl.ohguohgutalk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/chats")
public class ChatController {

    /**
     * 채팅방 목록 보여주는 get 메핑
     * @return chats.html
     */
    @GetMapping("")
    public String getChatList() {
        // List 가져와서 mv에 넣고 return 하기
        // 여기서는 채팅방목록 조회해서 내려주기 ㅇㅇ
        // - 근데 일단 채팅 연결하는거 먼저 ㅇㅇ
        return "chats";
    }

    /**
     * 채팅방 이동
     * @return chat.html
     */
    @GetMapping("/chat")
    public String startChat() {
        return "chat";
    }
}
