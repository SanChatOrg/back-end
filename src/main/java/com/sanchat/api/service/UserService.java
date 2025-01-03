package com.sanchat.api.service;

import com.sanchat.api.dto.UserDTO;

public interface UserService {
    void createUser(UserDTO userDTO);

    UserDTO getUser(Long userNo);
}
