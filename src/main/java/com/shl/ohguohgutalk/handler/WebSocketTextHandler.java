package com.shl.ohguohgutalk.handler;

//import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketTextHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    // WebSocketSession : 웹소켓이 연결될 때 생기는 연결정보를 담고 있는 객체

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 웹소켓 커넥션이 맺어지는 경우 add()
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
//        JSONObject jsonObject = new JSONObject(payload);
        for (WebSocketSession s : sessions) {
//            s.sendMessage(new TextMessage("Hi " + jsonObject.getString("user") + "!"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 웹소켓 커넥션이 끊어지는 경우 remove()
        sessions.remove(session);
    }
}
