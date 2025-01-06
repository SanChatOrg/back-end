package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.WalkDTO;
import com.sanchat.api.mapper.WalkMapper;
import com.sanchat.api.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalkServiceImpl implements WalkService {

    @Autowired
    WalkMapper walkMapper;

    @Override
    public void walkRegist(WalkDTO dto) {
        walkMapper.walkRegist(dto);
    }
}
