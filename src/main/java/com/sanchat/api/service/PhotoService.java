package com.sanchat.api.service;

import com.sanchat.api.dto.PhotoDTO;

import java.util.List;

public interface PhotoService {
    List<PhotoDTO> getImageList(String type, Long id);
}
