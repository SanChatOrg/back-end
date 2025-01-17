package com.sanchat.api.serviceImpl;

import com.sanchat.api.repository.EmitterRepository;
import com.sanchat.api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final static Long DEFAULT_TIMEOUT = 3600000L;
    private final static String NOTIFICATION_NAME = "notify";

    private final EmitterRepository emitterRepository;

    @Override
    public SseEmitter connectNotification(String userId) {
        // 새로운 SseEmitter 만든다.
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

        // user id 로 SseEmitter 저장
        emitterRepository.save(userId, sseEmitter);

        // 세션 종료 시 저장한 SseEmitter 삭제
        sseEmitter.onCompletion(() -> emitterRepository.delete(userId));
        sseEmitter.onTimeout(() -> emitterRepository.delete(userId));

        // 503 오류 발생 않도록 첫 데이터 전송
        try {
            sseEmitter.send(SseEmitter.event().id("").name(NOTIFICATION_NAME).data("Connection completed"));
        } catch (Exception e) {
            throw new ApplicationContextException(e.getMessage());
        }

        return sseEmitter;
    }
}
