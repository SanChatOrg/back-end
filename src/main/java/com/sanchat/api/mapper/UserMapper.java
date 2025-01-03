package com.sanchat.api.mapper;

import com.sanchat.api.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(user_name, user_birth, user_id, user_pw, user_intro) values (#{user.userName}, #{user.userBirth}, #{user.userId}, #{user.userPw}, #{user.userIntro})")
    @Options(useGeneratedKeys = true, keyProperty = "userNo")
    void createUser(@Param("user") UserDTO userDTO);

    @Select("select * from user where user_no=#{userNo}")
    @Results(@Result(property = "dogs", column="user_no", many = @Many(select="com.sanchat.api.DogMapper.getDogs")))
    UserDTO getUser(@Param("userNo") Long userNo);
}
