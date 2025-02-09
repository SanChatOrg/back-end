package com.sanchat.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityReplyDTO {
    private Long replyNo;
    private Long replyParentNo;
    private String replyContent;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String replyDeleted;
    private Long communityNo;
    private Long userNo;

    private String userName;
    private String profilePhoto;
}
