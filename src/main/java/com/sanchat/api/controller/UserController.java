package com.sanchat.api.controller;

import com.sanchat.api.dto.DogDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
        System.out.println(userService.getDogList(userId));
        return userService.getDogList(userId);
    }

}
