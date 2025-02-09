package com.sanchat.api.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sanchat.api.dto.DogDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.dto.UserMDTO;
import com.sanchat.api.service.UserService;
import lombok.Builder;
import org.apache.catalina.User;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    UserService userService;

//    Map<String, UserMDTO> userList = new HashMap<>();

    Map<String, UserMDTO> userList = new HashMap<>();

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

        System.out.println(message.getPayload());

        String userId = jsonObject.getString("userId");
        String latitude = jsonObject.getString("latitude");
        String longitude = jsonObject.getString("longitude");
        String type = jsonObject.getString("type");


        UserDTO userDTO = userService.getUser(userId); // 정보 조회해오기
        UserMDTO userMDTO = new UserMDTO(); // 위도경도도 포함된

        userMDTO.setUserId(userId);
        userMDTO.setPhoto(userDTO.getPhoto().getPhotoUrl());
        userMDTO.setUserName(userDTO.getUserName());
        userMDTO.setUserIntro(userDTO.getUserIntro());

        List<String> dogList = new ArrayList<>();
        for(DogDTO dDto : userDTO.getDogList()){
            dogList.add(dDto.getDogName());
        }
        userMDTO.setDogList(dogList);

        userMDTO.setLatitude(Double.parseDouble(latitude));
        userMDTO.setLongitude(Double.parseDouble(longitude));


        userList.put(userId, userMDTO); // 소켓에 등록 된 사람 정보 저장

        System.out.println(id +" :: " + userId + " 님의 위치 정보 | " +latitude + " | " +longitude + type );


        switch(type) {
            case "CLOSE" :
                CLIENTS.remove(session.getId());
                System.out.println(id + " :: 접속 종료");
                break;
            default:
                break;
        }



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

    public  Map<String, UserMDTO> getSocketList() {
        return userList;
    }

//    public ConcurrentHashMap<String, WebSocketSession> getSocketList() {
//        return CLIENTS;
//    }
}
