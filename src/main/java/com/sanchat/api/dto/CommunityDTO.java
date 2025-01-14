package com.sanchat.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDTO {
    private Long communityNo;
    private String communityContent;
    private String filePath;
    private String filePublicId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long communityViewCount;
    private Long communityLikeCount;
    private Long communityReplyCount;
    private String communityDeleted;
    private Long userNo;
}
