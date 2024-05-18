package com.shl.ohguohgutalk.dto;

import lombok.Builder;

/**
 * 채팅방 DTO
 * @param type 메시지 타입 | enter: 입장 | talk: 대화 | leave : 퇴장
 * @param roomId 방 번호
 * @param sender 메시지 보내는 사람
 * @param message 메시지
 * @param time 메시지 발송 시간
 */
@Builder
public record ChatDTO(MessageType type, String roomId, String sender, String message, String time) {
    // *record : MongoDB 와 상호작용은 가능하나, Spring Data mongoDB는 record 를 지원하지 않음

    public enum MessageType {
        ENTER, TALK, LEAVE;
    }
}
