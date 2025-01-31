package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.ChatParticipantDTO;
import com.sanchat.api.dto.ChatRoomDTO;
import com.sanchat.api.mapper.ChatParticipantMapper;
import com.sanchat.api.mapper.ChatRoomMapper;
import com.sanchat.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("chatRoomService")
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;
    private final ChatParticipantMapper chatParticipantMapper;

    @Override
    public List<ChatRoomDTO> getAllChatRoomsByUserNo(Long userNo) {
        return chatRoomMapper.findAllByUserNo(userNo);
    }

    @Override
    @Transactional
    public int addChatRoom(ChatRoomDTO chatRoomDTO, Long userNo) {
        chatRoomMapper.save(chatRoomDTO);
        Long chatRoomNo = chatRoomDTO.getChatRoomNo();
        log.info("chatRoomNo: {}", chatRoomNo);
        return chatParticipantMapper.save(ChatParticipantDTO.builder().chatRoomNo(chatRoomNo).userNo(userNo).build());
    }

    @Override
    public int updateChatRoom(ChatRoomDTO chatRoomDTO) {
        return chatRoomMapper.save(chatRoomDTO);
    }
}
