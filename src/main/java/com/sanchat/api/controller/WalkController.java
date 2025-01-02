package com.sanchat.api.controller;

import com.sanchat.api.dto.WalkDTO;
import com.sanchat.api.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/walk")
public class WalkController {
    @Autowired
    private WalkService walkService;

    @GetMapping("/insert")
    public void insertWalk(WalkDTO walkDTO) {
        walkService.insertWalk(walkDTO);
    }

    @GetMapping("/delete")
    public void deleteWalk(Long walkNo) {
        walkService.deleteWalk(walkNo);
    }

    @GetMapping("/select")
    public List<WalkDTO> getAllWalk() {
        return walkService.getAllWalk();
    }
}
