package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.FollowDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.dto.UserFDTO;
import com.sanchat.api.mapper.FollowMapper;
import com.sanchat.api.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    FollowMapper followMapper;

    public List<UserDTO> getFollowList(int userNo) {
        return followMapper.getFollowList(userNo);
    }

    @Override
    public List<UserFDTO> getFollowerList(int userNo) {
        return followMapper.getFollowerList(userNo);
    }

    @Override
    public void followUser(FollowDTO followDTO) {
         followMapper.followUser(followDTO);
    }

    @Override
    public void unfollowUser(FollowDTO followDTO) {
         followMapper.unfollowUser(followDTO);
    }


}
