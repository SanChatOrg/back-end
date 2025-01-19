package com.sanchat.api.controller;

import com.sanchat.api.dto.FollowDTO;
import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.dto.UserFDTO;
import com.sanchat.api.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@CrossOrigin(origins = "*")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping("/getFollowList")
    public List<UserDTO> getFollowList(@RequestParam int userNo){
        return followService.getFollowList(userNo);
    }


    @GetMapping("/getFollowerList")
    public List<UserFDTO> getFollowerList(@RequestParam int userNo){
        return followService.getFollowerList(userNo);
    }

    @PostMapping("/followUser")
    public void followUser(@RequestBody FollowDTO followDTO){

        int follower = followDTO.getFollowerNo();
        int followee = followDTO.getFolloweeNo();

        System.out.println(follower + " + " +  followee + "--------------------");
        followService.followUser(followDTO);
    }

    @PostMapping("/unfollowUser")
    public void unfollowUser(@RequestBody FollowDTO followDTO){
        int follower = followDTO.getFollowerNo();
        int followee = followDTO.getFolloweeNo();
        followService.unfollowUser(followDTO);
    }

}
