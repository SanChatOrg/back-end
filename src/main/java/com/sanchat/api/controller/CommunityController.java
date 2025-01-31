package com.sanchat.api.controller;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.CommunityReplyDTO;
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

    // CommunityDetail
    @GetMapping("/getDetail/{communityNo}")
    public CommunityDTO getDetail(@PathVariable Long communityNo) {
        return communityService.getDetail(communityNo);
    }

    // CommunityDetail reply
    @PostMapping("/newReply/{communityNo}")
    public CommunityReplyDTO newReply(
            @PathVariable("communityNo") Long communityNo,
            @RequestParam("replyContent") String replyContent,
            @RequestParam("userNo") Long userNo,
            @RequestParam(value = "replyParentNo", required = false) Long replyParentNo) {
        return communityService.newReply(communityNo, replyContent, userNo, replyParentNo);
    }

    // CommunityDetail reply
    @GetMapping("/getReply/{communityNo}")
    public List<CommunityReplyDTO> getReply(@PathVariable Long communityNo) {
        return communityService.getReply(communityNo);
    }

    // CommunityDetail reply delete
    @PutMapping("/deleteReply/{communityNo}/{replyNo}")
    public CommunityReplyDTO deleteReply(
            @PathVariable("communityNo") Long communityNo,
            @PathVariable("replyNo") Long replyNo) {
        return communityService.deleteReply(communityNo, replyNo);
    }

    // CommunityDetail post delete
    @PutMapping("/deletePost/{communityNo}")
    public CommunityDTO deletePost(@PathVariable("communityNo") Long communityNo) {
        return communityService.deletePost(communityNo);
    }

    // CommunityMain
    @GetMapping("/getAllPost")
    public List<CommunityDTO> getAllPost() {
        return communityService.getAllPost();
    }

    // CommunityMain Like
//    @PostMapping("/like/{communityNo}")
//    public

}
