package com.sanchat.api.mapper;

import com.sanchat.api.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

    List<ChatRoomDTO> findAllByUserNo(Long userId);
    int save(ChatRoomDTO dto);
}
