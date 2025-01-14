package com.sanchat.api.mapper;

import com.sanchat.api.dto.WalkDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalkMapper {

    void walkRegist(WalkDTO dto);

}
