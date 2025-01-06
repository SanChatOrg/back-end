package com.sanchat.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityLikeDTO {
    private Long likeNo;
    private LocalDateTime createdAt;
    private String isLiked;
    private Long userNo;
    private Long communityNo;
}
