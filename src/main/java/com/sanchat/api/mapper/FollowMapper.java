package com.sanchat.api.mapper;

import com.sanchat.api.dto.FollowDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.dto.UserFDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

    List<UserDTO> getFollowList(int userNo);
    List<UserFDTO> getFollowerList(int userNo);
    void followUser(FollowDTO followDTO);
    void unfollowUser(FollowDTO followDTO);

}
