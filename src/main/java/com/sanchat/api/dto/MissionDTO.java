package com.sanchat.api.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@ToString
@Builder
public class MissionDTO {
    private Long missionNo;
    private String missionName;
}
