package com.sanchat.api.controller;

import com.sanchat.api.dto.WalkDTO;
import com.sanchat.api.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/walk")
@CrossOrigin(origins = "*")
public class WalkController {

    @Autowired
    WalkService walkService;

    @PostMapping("/walkData")
    public ResponseEntity<String> walkData(@RequestBody WalkDTO data){

        System.out.println(data);
        walkService.walkRegist(data);
        return ResponseEntity.ok("Data received successfully.");
    }

    @GetMapping("/getWalkList")
    public List<WalkDTO> getWalkList(){
        return walkService.walkList();
    }


}
