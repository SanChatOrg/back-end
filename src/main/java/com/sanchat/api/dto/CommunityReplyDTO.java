package com.sanchat.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityReplyDTO {
    private Long replyNo;
    private String replyContent;
    private LocalDateTime createdAt;
    private Long replyParentNo;
    private Long communityNo;
}
