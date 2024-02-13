package com.shl.ohguohgutalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chats")
public class ChatController {

    /**
     * 채팅방 목록 보여주는 get 메핑
     * @return chatList.html
     */
    @GetMapping("/")
    public String getChatList(ModelAndView mv) {
        // List 가져와서 mv에 넣고 return 하기


        return "chatList";
    }
}
