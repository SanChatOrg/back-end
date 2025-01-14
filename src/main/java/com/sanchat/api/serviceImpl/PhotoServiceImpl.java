package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.PhotoDTO;
import com.sanchat.api.mapper.PhotoMapper;
import com.sanchat.api.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<PhotoDTO> getImageList(String type, Long id) {
        return photoMapper.getImageList(type, id);
    }
}
