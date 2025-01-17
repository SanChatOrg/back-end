package com.sanchat.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmitterRepository {

    // 유저ID를 키로 SseEmitter를 해시맵에 저장할 수 있도록 구현했다.
    private Map<String, SseEmitter> emitterMap = new HashMap<>();

    public SseEmitter save(String userId, SseEmitter sseEmitter) {
        emitterMap.put(getKey(userId), sseEmitter);
        return sseEmitter;
    }

    public Optional<SseEmitter> get(String userId) {
        return Optional.ofNullable(emitterMap.get(getKey(userId)));
    }

    public void delete(String userId) {
        emitterMap.remove(getKey(userId));
    }

    private String getKey(String userId) {
        return "Emitter:UID:" + userId;
    }
}
