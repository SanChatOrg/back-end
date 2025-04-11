package com.sanchat.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotNull(message = "아이디 입력은 필수입니다.")
    private String userId;

    @NotNull(message = "패스워드 입력은 필수입니다.")
    private String password;


}
