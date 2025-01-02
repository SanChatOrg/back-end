package com.sanchat.api.controller;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "*")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @GetMapping("/get")
    public String getCommunity() {
        return "커뮤니티 테스트";
    }

    @PostMapping("/test")
    public CommunityDTO testCommunity() {
        return communityService.testCommunity();
    }

}
