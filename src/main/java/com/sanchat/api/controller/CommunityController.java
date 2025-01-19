package com.sanchat.api.controller;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.service.CommunityService;
import com.sanchat.api.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "*")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private PhotoService photoService;

    // 서버 연결
    @GetMapping("/get")
    public String getCommunity() {
        return "Community 연결";
    }

    // CommunityWrite
    @PostMapping(value = "/newPost", consumes = "multipart/form-data")
    public CommunityDTO newPost(
            @RequestPart("communityContent") String communityContent,
            @RequestPart("files") List<MultipartFile> files) throws IOException {
        return communityService.newPost(communityContent, files);
    }

    // CommunityEdit
    @GetMapping("/getPost/{communityNo}")
    public CommunityDTO getPost(@PathVariable Long communityNo) {
        return communityService.getPost(communityNo);
    }

    // CommunityEdit
    @PutMapping(value = "/editPost/{communityNo}", consumes = "multipart/form-data")
    public CommunityDTO editPost(
            @PathVariable Long communityNo,
            @RequestParam(value = "communityContent", required = false) String communityContent,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @RequestParam(value = "photoIdsToDelete", required = false) List<Long> photoIdsToDelete
            ) throws IOException {

        CommunityDTO updatePost = communityService.editPost(communityNo, communityContent, files, photoIdsToDelete);

        return updatePost;
    }

}
