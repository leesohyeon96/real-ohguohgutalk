package com.shl.ohguohgutalk.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Data
public class ChatRoomDTO {
    /** 채팅방 아이디 */
    private String chatroom_id;
    /** 채팅방 이름 */
    private String chatroom_title;
    /** 채팅방 인원수 */
    private int userCount;
    /** 채팅방 생성 날짜 */
    private LocalDateTime created_dt;


    private HashMap<String, String> userList = new HashMap<>();

    /**
     * 채팅방 생성하는 메소드
     * @param roomName 채팅방 이름
     * @return 채팅방
     */
    public ChatRoomDTO create(String roomName) {
        ChatRoomDTO chatRoom = new ChatRoomDTO();
        chatRoom.chatroom_id = UUID.randomUUID().toString();
        chatRoom.chatroom_title = roomName;
        chatRoom.userCount = userCount;
        chatRoom.created_dt = created_dt;

        return chatRoom;
    }
}
