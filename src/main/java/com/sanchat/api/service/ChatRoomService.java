package com.sanchat.api.service;

import com.sanchat.api.dto.ChatRoomDTO;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomDTO> getAllChatRoomsByUserNo(Long userNo);
    int addChatRoom(ChatRoomDTO chatRoomDTO, Long userNo);
    int updateChatRoom(ChatRoomDTO chatRoomDTO);

}
