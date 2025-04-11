package com.sanchat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPwDto {

    private Long userNo;
    private String userId;
    private String userPw;
    private String userName;
}

