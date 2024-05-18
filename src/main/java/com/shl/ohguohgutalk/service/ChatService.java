package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.dto.ChatRoomDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {
    private Map<String, ChatRoomDTO> chatRoomMap;

    @PostConstruct
    private void init() {
        // 채팅방 생성 순서를 최근순으로 반환하기 위해 순서가 중요한 LinkedHashMap 사용한듯 함
        chatRoomMap = new LinkedHashMap<>();
    }

    // 전체 채팅방 조회
    public List<ChatRoomDTO> findAllChatRoom() {
        List<ChatRoomDTO> chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    // 채팅방 1개 조회?





}
