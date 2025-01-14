package com.sanchat.api.mapper;

import com.sanchat.api.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(user_name, user_birth, user_id, user_pw, user_intro) values (#{user.userName}, #{user.userBirth}, #{user.userId}, #{user.userPw}, #{user.userIntro})")
    @Options(useGeneratedKeys = true, keyProperty = "userNo")
    void createUser(@Param("user") UserDTO userDTO);

    UserDTO getUser(Long userNo);
}
