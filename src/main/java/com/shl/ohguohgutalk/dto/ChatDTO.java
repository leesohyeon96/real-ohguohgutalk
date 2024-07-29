package com.shl.ohguohgutalk.dto;

import lombok.*;

/**
 * 채팅방 DTO
 */
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatDTO {
    // *record : MongoDB 와 상호작용은 가능하나, Spring Data mongoDB는 record 를 지원하지 않음
    // record 는 setter 제공하기 않기 때문에 역사속으로..
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;
    public enum MessageType {
        ENTER, TALK, LEAVE;

    }
}

