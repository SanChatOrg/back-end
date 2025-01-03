package com.sanchat.api.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
    private Long userNo;
    private String userName;
    private LocalDate userBirth;
    private String userId;
    private String userPw;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String userIntro;
    private List<DogDTO> dogs;
}
