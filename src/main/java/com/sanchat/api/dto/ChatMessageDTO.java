package com.sanchat.api.dto;

import com.sanchat.api.enums.ChatMessageContentEnum;
import com.sanchat.api.enums.ChatMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDTO {

    private Long chatMessageNo;
    private ChatMessageEnum messageType;
    private ChatMessageContentEnum messageContentType;
    private Long chatRoomNo;
    private Long userNo;
    private String messageContent;
    private LocalDateTime sentAt;
}
