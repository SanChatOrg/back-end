package com.sanchat.api.mapper;

import com.sanchat.api.dto.DogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DogMapper {
    @Select("select * from dog where user_no=#{userNo}")
    List<DogDTO> getDogs(@Param("userNo") Long userNo);
}
