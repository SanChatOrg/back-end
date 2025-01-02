package com.sanchat.api.service;

import com.sanchat.api.dto.ChatRoomDTO;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomDTO> getAllChatRoomsByUserNo(Long userNo);
    int saveChatRoom(ChatRoomDTO chatRoomDTO);

}
