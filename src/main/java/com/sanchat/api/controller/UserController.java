package com.sanchat.api.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanchat.api.config.MapWebSocketHandler;
import com.sanchat.api.dto.*;
import com.sanchat.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    MapWebSocketHandler mapWebSocketHandler;

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/test")
    public long test(@RequestParam String userId){
        System.out.println(userService.getUserNo(userId) + " userNo 가져오기");
        return userService.getUserNo(userId);
    }

    @GetMapping("/getDogList")
    public List<DogDTO> getDogList(@RequestParam String userId){
        System.out.println(userId);
        System.out.println(userService.getDogList(userId) + "독독독");
        return userService.getDogList(userId);
    }

    @GetMapping("/socketList")
    public Map<String, UserMDTO> getSocketList() {
        return mapWebSocketHandler.getSocketList();
    }

    @GetMapping("/getPhotoList")
    public List<CommunityDTO> getPhotoList(@RequestParam String userId){
        return userService.getPhotoList(userId);
    }

/*    @GetMapping("/socketList")
    public List<WebSocketSessionDTO> getSocketList() {
        List<WebSocketSessionDTO> sessionDTOList = new ArrayList<>();

        mapWebSocketHandler.getSocketList().forEach((key, session) -> {
            WebSocketSessionDTO sessionDTO = new WebSocketSessionDTO(
                    session.getId(),
                    session.getAttributes()
            );
            sessionDTOList.add(sessionDTO);
        });

        return sessionDTOList;
    }*/

}
