package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.mapper.CommunityMapper;
import com.sanchat.api.service.CloudinaryService;
import com.sanchat.api.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public CommunityDTO newPost(String communityContent, MultipartFile file) throws IOException {

        String uploadedUrl = cloudinaryService.upload(file);

        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityContent(communityContent)
                .filePath(uploadedUrl)
                .createdAt(LocalDateTime.now())
                .build();

        communityMapper.newPost(communityDTO);

        return communityDTO;
    }
}
