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
public class UserFDTO {

    private Long userNo;
    private String userName;
    private String userId;
    private String userIntro;
    private Boolean isFollowed;

    private PhotoDTO photo;
}
