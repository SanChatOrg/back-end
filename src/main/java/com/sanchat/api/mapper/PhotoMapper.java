package com.sanchat.api.mapper;

import com.sanchat.api.dto.PhotoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PhotoMapper {
    List<PhotoDTO> getImageList(@Param("type") String type, @Param("id") Long id);

    void insertPhoto(PhotoDTO photoDTO);

    void updatePhoto(PhotoDTO photoDTO);

    void deletePhoto(Long photoNo);

    PhotoDTO getPhotoById(Long photoNo);
}
