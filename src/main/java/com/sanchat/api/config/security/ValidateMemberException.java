package com.sanchat.api.config.security;  // 패키지 경로 확인

public class ValidateMemberException extends RuntimeException {

    public ValidateMemberException() {
        super("Invalid member operation");  // 기본 메시지
    }

    public ValidateMemberException(String message) {
        super(message);  // 전달된 메시지 사용
    }
}
