package com.sanchat.api.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    SseEmitter connectNotification(String userId);
}
