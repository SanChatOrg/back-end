package com.sanchat.api.controller;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "*")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @GetMapping("/get")
    public String getCommunity() {
        return "CommunityWrite 연결";
    }

    @PostMapping(value = "/newPost", consumes = "multipart/form-data")
    public CommunityDTO newPost(
            @RequestPart("communityContent") String communityContent,
            @RequestPart("file") MultipartFile file) throws IOException {

        CommunityDTO communityDTO = communityService.newPost(communityContent, file);

        System.out.println("업로드 완료: " + communityDTO);

        return communityDTO;
    }
}
