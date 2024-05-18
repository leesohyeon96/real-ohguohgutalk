package com.shl.ohguohgutalk.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "chats")
@Setter
@Getter
@ToString
public class Chat {
    @Id
    private String id;
    private String message;
    private LocalDateTime localDateTime; // localtime > utc 로 저장될 것? (지금은 +09:00 고정)

    public static Chat of(String message, LocalDateTime localDateTime) {
        Chat chat = new Chat();
        chat.setMessage(message);
        chat.setLocalDateTime(localDateTime);
        return chat;
    }
}
