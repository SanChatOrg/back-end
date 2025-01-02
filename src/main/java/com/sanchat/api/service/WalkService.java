package com.sanchat.api.service;

import com.sanchat.api.dto.WalkDTO;

import java.util.List;

public interface WalkService {
    void insertWalk(WalkDTO walkDTO);

    void deleteWalk(Long walkNo);

    List<WalkDTO> getAllWalk();
}
