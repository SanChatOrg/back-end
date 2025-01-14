package com.sanchat.api.controller;

import com.sanchat.api.dto.PhotoDTO;
import com.sanchat.api.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/getImageList")
    public List<PhotoDTO> getImageList(
            @RequestParam String type,
            @RequestParam Long id
    ) {
        System.out.println(type + " " + id);
        return photoService.getImageList(type, id);
    }
}
