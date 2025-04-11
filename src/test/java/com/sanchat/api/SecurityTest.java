package com.sanchat.api;

import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.mapper.UserMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SecurityTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void userTest() {
        UserDTO result = userMapper.getUser(22);

    }

}
