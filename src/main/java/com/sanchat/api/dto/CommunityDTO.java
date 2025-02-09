package com.sanchat.api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDTO {
    private Long communityNo;
    private String communityContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long communityViewCount;
    private Long communityLikeCount;
    private Long communityReplyCount;
    private String communityDeleted;
    private Long userNo;

    private List<PhotoDTO> photoList;

    private String userName;
    private String profilePhoto;

    private String isLiked; // 'y' or 'n'
}
