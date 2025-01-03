package com.sanchat.api;

import com.sanchat.api.mapper.UserMapper;
import com.sanchat.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetUserWithDogList() {
        System.out.println(userMapper.getUser(1L));
    }
}
