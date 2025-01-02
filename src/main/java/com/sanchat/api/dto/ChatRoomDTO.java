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
public class ChatRoomDTO {
    private Long chatRoomNo;
    private String chatRoomName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isGroupChat;
}
