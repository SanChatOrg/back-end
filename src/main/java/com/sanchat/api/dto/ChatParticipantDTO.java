package com.sanchat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatParticipantDTO {
    private Long chatPartNo;
    private Long userNo;
    private Long chatRoomNo;
    private LocalDateTime joinedAt;
}
