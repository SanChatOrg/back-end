package com.sanchat.api.service;

import com.sanchat.api.dto.PhotoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    List<PhotoDTO> getImageList(String type, Long id);

    PhotoDTO insertPhoto(MultipartFile file, String relatedType, Long relatedEntityId) throws IOException;

    void updatePhoto(PhotoDTO photoDTO);

    void savePhoto(PhotoDTO newPhoto);

    void deletePhoto(Long photoNo);

    PhotoDTO getPhotoById(Long photoId);
}
