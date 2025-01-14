package com.sanchat.api.mapper;

import com.sanchat.api.dto.WalkDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WalkMapper {

    void walkRegist(WalkDTO dto);
    List<WalkDTO> walkList();
    List<WalkDTO> getAllWalk();
}
