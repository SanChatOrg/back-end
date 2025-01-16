package com.sanchat.api.mapper;

import com.sanchat.api.dto.ChatParticipantDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatParticipantMapper {

    int save(ChatParticipantDTO dto);

}
