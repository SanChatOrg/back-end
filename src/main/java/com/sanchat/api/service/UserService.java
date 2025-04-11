package com.sanchat.api.service;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.DogDTO;
import com.sanchat.api.dto.UserDTO;

import java.util.List;

public interface UserService {
    void createUser(UserDTO userDTO);

    UserDTO getUser(String username);

    int getUserNo(String username);

    List<DogDTO> getDogList(String username);

    List<CommunityDTO> getPhotoList(String username, int limit, int offset);
}

