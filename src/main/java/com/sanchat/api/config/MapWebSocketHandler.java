package com.sanchat.api.config;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapWebSocketHandler extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS =
                new ConcurrentHashMap<String, WebSocketSession>();



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String id = session.getId();  //메시지를 보낸 아이디
//        System.out.println(id);
//        System.out.println(session);
//        System.out.println(message);
//        System.out.println(message.getPayload());
//        System.out.println(String.valueOf(message.getPayload()));


        JSONObject jsonObject = new JSONObject(String.valueOf(message.getPayload()));
        String name = jsonObject.getString("name");
        String latitude = jsonObject.getString("latitude");
        String longitude = jsonObject.getString("longitude");

        System.out.println(id +" :: " + name + " 님의 위치 정보 | " +latitude + " | " +longitude);


        CLIENTS.entrySet().forEach( arg->{
            if(!arg.getKey().equals(id)) {  //같은 아이디가 아니면 메시지를 전달합니다.
                try {
                    arg.getValue().sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        CLIENTS.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
