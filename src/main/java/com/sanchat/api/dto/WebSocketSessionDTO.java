package com.sanchat.api.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WebSocketSessionDTO {
        private String sessionId;
        private Map<String, Object> attributes;

}
