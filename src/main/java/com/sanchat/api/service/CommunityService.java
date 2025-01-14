package com.sanchat.api.service;

import com.sanchat.api.dto.CommunityDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommunityService {
    CommunityDTO newPost(String communityContent, MultipartFile file) throws IOException;
}
