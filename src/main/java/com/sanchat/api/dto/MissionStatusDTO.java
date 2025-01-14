package com.sanchat.api.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@ToString
@Builder
public class MissionStatusDTO {
    private Long missionStatusNo;
    private Long missionNo;
    private Long dogNo;
    private String grade;
}
