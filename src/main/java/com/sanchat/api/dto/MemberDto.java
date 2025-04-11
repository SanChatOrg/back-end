package com.sanchat.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String role;
}

