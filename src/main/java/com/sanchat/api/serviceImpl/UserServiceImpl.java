package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.mapper.UserMapper;
import com.sanchat.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(UserDTO userDTO) {
        userMapper.createUser(userDTO);
    }
}
