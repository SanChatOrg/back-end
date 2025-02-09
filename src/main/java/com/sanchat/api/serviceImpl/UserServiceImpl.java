package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.DogDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.mapper.UserMapper;
import com.sanchat.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(UserDTO userDTO) {
        userMapper.createUser(userDTO);
    }

    @Override
    public UserDTO getUser(String username) {
//        return userMapper.getUserProfileData(username);
        long userNo = userMapper.getUserNo(username);
        System.out.println(userMapper.getUser(userNo));
        return userMapper.getUser(userNo);
    }

    @Override
    public int getUserNo(String username){
        return userMapper.getUserNo(username);
    }

    @Override
    public List<DogDTO> getDogList(String username){
        return userMapper.getDogList(username);
    }

    @Override
    public List<CommunityDTO> getPhotoList(String username) {
        long userNo = userMapper.getUserNo(username);
        System.out.println(userMapper.getPhotoList(userNo) + "\n ---------- userPhotoList");
        return userMapper.getPhotoList(userNo);
    }

}
