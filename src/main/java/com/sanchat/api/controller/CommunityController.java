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

    // 서버 연결
    @GetMapping("/get")
    public String getCommunity() {
        return "Community 연결";
    }

    // CommunityWrite
    @PostMapping(value = "/newPost", consumes = "multipart/form-data")
    public CommunityDTO newPost(
            @RequestPart("communityContent") String communityContent,
            @RequestPart("file") MultipartFile file) throws IOException {
        CommunityDTO communityDTO = communityService.newPost(communityContent, file);
        return communityDTO;
    }

    // CommunityEdit
    @GetMapping("/getPost/{communityNo}")
    public CommunityDTO getPost(@PathVariable Long communityNo) {
        return communityService.getPost(communityNo);
    }

    @PutMapping(value = "/editPost/{communityNo}", consumes = "multipart/form-data")
    public CommunityDTO editPost(
            @PathVariable Long communityNo,
            @RequestPart(value = "communityContent", required = false) String communityContent,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        CommunityDTO oldPost = communityService.getPost(communityNo);

        if ((oldPost.getFilePath() == null || oldPost.getFilePath().isEmpty()) && (file == null || file.isEmpty())) {
            throw new IllegalArgumentException("선택된 사진이 없습니다.");
        }
        return communityService.editPost(communityNo, communityContent, file);
    }

}
