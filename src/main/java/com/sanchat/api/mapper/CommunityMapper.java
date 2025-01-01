package com.sanchat.api.mapper;

import com.sanchat.api.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMapper {
    CommunityDTO selectTestCommunity();
}
