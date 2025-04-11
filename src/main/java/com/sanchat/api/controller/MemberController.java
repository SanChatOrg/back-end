package com.sanchat.api.controller;

import com.sanchat.api.dto.LoginRequestDto;
import com.sanchat.api.dto.MemberDto;
import com.sanchat.api.dto.MemberRequestDto;
import com.sanchat.api.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @PostMapping("login")
    public ResponseEntity<?> getMemberProfile(@Valid @RequestBody LoginRequestDto request) {
        System.out.println(request);
        String token = memberService.login(request);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);

        return ResponseEntity.ok(result);
    }

    @PostMapping("signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody MemberRequestDto member) {
//        MemberDto entity = modelMapper.map(member, MemberDto.class);

        MemberDto entity = new MemberDto();
        entity.setUserId(member.getUserId());
        entity.setPassword(member.getPassword());
        Long id = memberService.signup(entity);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
