package com.sanchat.api.mapper;

import com.sanchat.api.dto.WalkDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WalkMapper {
    @Insert("insert into walk(walk_time, walk_distance, walk_date) values(#{walkDTO.walkTime}, #{walkDTO.walkDistance}, #{walkDTO.walkDate})")
    @Options(useGeneratedKeys = true, keyProperty = "walkNo")
    void insertWalk(@Param("walkDTO") WalkDTO walkDTO);

    @Delete("delete from walk where walk_no=#{walkNo}")
    void deleteWalk(Long walkNo);

    @Select("select * from walk order by walk_no desc")
    List<WalkDTO> getAllWalk();
}
