package com.sanchat.api.controller;

import com.sanchat.api.dto.UserDTO;
import com.sanchat.api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/subscribe")
    public SseEmitter subscribe(UserDTO userDTO) {
        // Authentication -> UserDTO upcasting 필요

        return notificationService.connectNotification(userDTO.getUserId());
    }
}
