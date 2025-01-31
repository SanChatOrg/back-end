package com.sanchat.api.service;

import com.sanchat.api.dto.FollowDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.dto.UserFDTO;

import java.util.List;

public interface FollowService {

    List<UserDTO> getFollowList(int userNo);
    List<UserFDTO> getFollowerList(int userNo);
    void followUser(FollowDTO followDTO);
    void unfollowUser(FollowDTO followDTO);
}
