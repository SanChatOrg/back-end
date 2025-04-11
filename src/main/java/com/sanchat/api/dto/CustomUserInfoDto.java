package com.sanchat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto extends MemberDto {

    private Long userNo;
    private String userId;
    private String password;
    private String userName;
    private String role;
}

