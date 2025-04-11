package com.sanchat.api.serviceImpl;

import java.util.Optional;

import com.sanchat.api.config.security.JwtUtil;
import com.sanchat.api.config.security.ValidateMemberException;
import com.sanchat.api.dto.CustomUserInfoDto;
import com.sanchat.api.dto.LoginRequestDto;
import com.sanchat.api.dto.MemberDto;
import com.sanchat.api.dto.MemberPwDto;
import com.sanchat.api.mapper.UserMapper;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;

    @Autowired
    UserMapper userMapper;

    @Transactional
    public String login(@Valid LoginRequestDto dto) {
        String id = dto.getUserId();
        String password = dto.getPassword();
        System.out.println(id + password);
        Optional<MemberPwDto> member = Optional.ofNullable(userMapper.getUserInfo(id));
        System.out.println(userMapper.getUserInfo(id));
        MemberPwDto dtoM = member.get();
        System.out.println(dtoM.getUserPw());

        if (member.isEmpty()) {
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
        }

        //암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
        if (!encoder.matches(password, member.get().getUserPw())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        CustomUserInfoDto info = modelMapper.map(member, CustomUserInfoDto.class);
        return jwtUtil.createAccessToken(info);
    }

    @Transactional
    public Long signup(MemberDto member) {
        // 이메일 중복 체크: userMapper에서 받은 결과가 null이면 이메일이 존재하지 않음
        Optional<MemberPwDto> validMember = Optional.ofNullable(userMapper.getUserInfo(member.getUserId()));

        if (validMember.isPresent()) {
            // 이메일이 이미 존재하면 예외를 던짐
            throw new ValidateMemberException("This member email is already exist: " + member.getUserId());
        }

        // 비밀번호 해시 처리
        member.setPassword(encoder.encode(member.getPassword()));

        // 회원 정보 생성
        userMapper.createUserM(member);

        // 생성된 회원의 ID 반환
        return member.getId();
    }


}