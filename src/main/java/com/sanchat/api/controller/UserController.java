package com.sanchat.api.controller;

import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public UserDTO getUser(@RequestParam Long userNo) {
        return userService.getUser(userNo);
    }
}
