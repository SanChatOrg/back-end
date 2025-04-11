package com.sanchat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto extends MemberDto {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String role;
}

