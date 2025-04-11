package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.MemberPwDto;
import com.sanchat.api.dto.UserMDTO;
import com.sanchat.api.mapper.UserMapper;
import com.sanchat.api.dto.UserDTO; // 사용자 DTO 클래스 (예시)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // userMapper의 메소드 이름과 매핑은 실제 구현에 따라 달라질 수 있습니다.
        MemberPwDto memberPwDto = userMapper.getUserInfo(username);

        if (memberPwDto == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 예시) UserDTO에 이미 암호화된 비밀번호가 있고, role 정보도 포함되어 있다고 가정합니다.
        // Spring Security의 User 객체를 사용하여 UserDetails 객체로 변환합니다.
        return User.builder()
                .username(memberPwDto.getUserName())      // 사용자 이름
                .password(memberPwDto.getUserPw())      // 암호화된 비밀번호
                .roles("USER")                        // 필요한 경우 여러 역할?을 설정 가능합니다.
                .build();
    }
}
