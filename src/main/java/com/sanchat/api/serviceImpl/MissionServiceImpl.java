package com.sanchat.api.serviceImpl;

import com.sanchat.api.mapper.MissionMapper;
import com.sanchat.api.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionServiceImpl implements MissionService {
    @Autowired
    private MissionMapper missionMapper;
}
