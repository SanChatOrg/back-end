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
    private String isLiked; // 'y' or 'n'
    private Long userNo;
    private Long communityNo;
}
