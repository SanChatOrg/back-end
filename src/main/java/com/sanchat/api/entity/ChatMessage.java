package com.sanchat.api.entity;

import com.sanchat.api.enums.ChatMessageContentEnum;
import com.sanchat.api.enums.ChatMessageEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "ChatMessage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChatMessage {
    @Field("chatMessageNo")
    private Long chatMessageNo;
    @Field("messageType")
    private ChatMessageEnum messageType;
    @Field("messageContentType")
    private ChatMessageContentEnum messageContentType;
    @Field("chatRoomNo")
    private Long chatRoomNo;
    @Field("userNo")
    private Long userNo;
    @Field("messageContent")
    private String messageContent;
    @Field("sentAt")
    private LocalDateTime sentAt;
}
