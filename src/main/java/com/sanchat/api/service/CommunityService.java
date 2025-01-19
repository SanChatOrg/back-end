package com.sanchat.api.service;

import com.sanchat.api.dto.CommunityDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommunityService {
    CommunityDTO newPost(String communityContent, List<MultipartFile> files) throws IOException;

    CommunityDTO getPost(Long communityNo);

    CommunityDTO editPost(Long communityNo, String communityContent, List<MultipartFile> files, List<Long> photoIdsToDelete) throws IOException;}
