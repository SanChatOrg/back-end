package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.mapper.CommunityMapper;
import com.sanchat.api.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public CommunityDTO testCommunity() {
        return communityMapper.selectTestCommunity();
    }
}
