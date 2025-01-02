package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.ChatRoomDTO;
import com.sanchat.api.mapper.ChatRoomMapper;
import com.sanchat.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("chatRoomService")
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;

    @Override
    public List<ChatRoomDTO> getAllChatRoomsByUserNo(Long userNo) {
        return chatRoomMapper.findAllByUserNo(userNo);
    }

    @Override
    public int saveChatRoom(ChatRoomDTO chatRoomDTO) {
        return chatRoomMapper.save(chatRoomDTO);
    }
}
