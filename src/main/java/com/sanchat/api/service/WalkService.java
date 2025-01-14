package com.sanchat.api.service;

import com.sanchat.api.dto.WalkDTO;


import java.util.List;

public interface WalkService {
    List<WalkDTO> getAllWalk();
    void walkRegist(WalkDTO dto);
    List<WalkDTO> walkList();

}
