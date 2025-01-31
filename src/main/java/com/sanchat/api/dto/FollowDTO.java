package com.sanchat.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FollowDTO {

    private int followNo;
    private int followerNo;
    private int followeeNo;
}
