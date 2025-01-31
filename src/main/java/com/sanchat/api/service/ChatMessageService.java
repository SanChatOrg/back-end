package com.sanchat.api.service;

import com.sanchat.api.entity.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage checkMessage(ChatMessage message);

    List<ChatMessage> findByChatRoomNo(Long chatRoomNo);
}
