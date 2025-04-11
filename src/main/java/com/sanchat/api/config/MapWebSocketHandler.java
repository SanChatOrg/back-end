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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Component // bean 으로 등록
public class MapWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    UserService userService;

    /**
    * 사용자 정보를 저장하는 MAP (접속한 사용자 리스트)
    * userID , UserMDTO 저장
    */
    Map<String, UserMDTO> userList = new HashMap<>();


    /**
    * 현재 접속한 클라이언트 (WebSocket 세션) 목록 저장
    */
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS =
                new ConcurrentHashMap<String, WebSocketSession>();


    /**
    * 클라이언트가 웹소켓 연결에 성공 시 호출
    */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);

        // 현재 접속 중인 사용자 목록을 JSON으로 변환
        JSONObject response = new JSONObject();
        response.put("type", "USER_LIST"); // 메시지 타입을 USER_LIST로 설정

        List<JSONObject> userArray = new ArrayList<>();

        for (UserMDTO user : userList.values()) {
                System.out.println(user.getUserId() + session.getId());
                JSONObject userJson = new JSONObject();
                userJson.put("userId", user.getUserId());
                userJson.put("userName", user.getUserName());
                userJson.put("photo", user.getPhoto());
                userJson.put("userIntro", user.getUserIntro());
                userJson.put("latitude", user.getLatitude());
                userJson.put("longitude", user.getLongitude());
                userJson.put("dogList", user.getDogList());
                userArray.add(userJson);
        }


        response.put("users", userArray);

        // 새로 접속한 사용자에게 현재 접속 중인 사용자 목록 전송
        session.sendMessage(new TextMessage(response.toString()));

    }

    /**
     * 클라이언트가 메세지를 보낼 때 호출
     * (사용자 위치 저장, 다른 클라이언트에 메세지 전달)
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {


        String id = session.getId();  //메시지를 보낸 웹소켓 세션 아이디

        // 받은 메시지를 JSON 형식으로 변환
        JSONObject jsonObject = new JSONObject(String.valueOf(message.getPayload()));

        // 필요한 정보 추출
        String userId = jsonObject.getString("userId");
        String latitude = jsonObject.getString("latitude");
        String longitude = jsonObject.getString("longitude");
        String type = jsonObject.getString("type"); // LOCATION, CLOSE , USER_LIST..

        UserDTO userDTO = userService.getUser(userId); // 사용자 정보 조회해오기

        // 사용자 위치 정보를 포함하는 UserDTO 객체 생성
        UserMDTO userMDTO = new UserMDTO();
        userMDTO.setUserId(userId);
        userMDTO.setPhoto(userDTO.getPhoto().getPhotoUrl());
        userMDTO.setUserName(userDTO.getUserName());
        userMDTO.setUserIntro(userDTO.getUserIntro());

        List<String> dogList = new ArrayList<>();
        for(DogDTO dDto : userDTO.getDogList()){
            dogList.add(dDto.getDogName());
        }
        userMDTO.setDogList(dogList);

        // 위도, 경도
        userMDTO.setLatitude(Double.parseDouble(latitude));
        userMDTO.setLongitude(Double.parseDouble(longitude));

        // 사용자 정보를 userList(Map) 에 저장
        userList.put(session.getId(), userMDTO);

        System.out.println(id +" :: " + userId + " 님의 위치 정보 | " +latitude + " | " +longitude + type );


        // 메세지 type = "CLOSE" 면 연결 종료, 세션 제거
        switch(type) {
            case "CLOSE" :
                CLIENTS.remove(session.getId());
                userList.remove(session.getId());
                break;
            default:
                break;
        }


        // 현제 접속 중인 모든 클라이언트에게 메시지 전달 (자신은 제외)
        CLIENTS.entrySet().forEach(arg -> {
            if(!arg.getKey().equals(id)) {  // 자신이 아니면 메시지를 전달합니다.
                try {
                    // 위치 정보도 포함하여 전달
                    JSONObject response = new JSONObject();
                    response.put("type", "LOCATION"); // 메시지 타입은 LOCATION
                    response.put("userId", userMDTO.getUserId());
                    response.put("userName", userMDTO.getUserName());
                    response.put("latitude", userMDTO.getLatitude());
                    response.put("longitude", userMDTO.getLongitude());
                    response.put("photo", userMDTO.getPhoto());
                    response.put("dogList", userMDTO.getDogList());

                    // 해당 클라이언트에게 메시지 전송
                    arg.getValue().sendMessage(new TextMessage(response.toString()));

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * WebSocket 전송 중 에러 발생 시 호출됨
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    /**
     * 클라이언트와의 연결이 종료될 때 호출됨
     * - 해당 클라이언트를 CLIENTS 목록에서 제거
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        CLIENTS.remove(session.getId());
        userList.remove(session.getId());
    }

    /**
     * 부분 메시지를 지원할지 여부를 반환 (현재는 false)
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
