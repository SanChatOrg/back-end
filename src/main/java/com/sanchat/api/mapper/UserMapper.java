package com.sanchat.api.mapper;

import com.sanchat.api.dto.*;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(user_name, user_birth, user_id, user_pw, user_intro) values (#{user.userName}, #{user.userBirth}, #{user.userId}, #{user.userPw}, #{user.userIntro})")
    @Options(useGeneratedKeys = true, keyProperty = "userNo")
    void createUser(@Param("user") UserDTO userDTO);

    @Insert("insert into user(user_name, user_birth, user_id, user_pw) values ('안녕', '2020-01-30', #{userId}, #{password})")
    Long createUserM(MemberDto dto);

    @Select("select * from user where user_id = #{userId}")
    MemberPwDto getUserInfo(String userId);

    @Update("update set pw ")
    void updatePassword(String pw);

    UserDTO getUserProfileData(String username);
    UserDTO getUser(long userNo);

    int getUserNo(String username);

    List<DogDTO> getDogList(String username);

    List<CommunityDTO> getPhotoList(long userNo);
}
