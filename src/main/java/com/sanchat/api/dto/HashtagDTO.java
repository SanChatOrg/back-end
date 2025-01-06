package com.sanchat.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashtagDTO {
    private Long hashtagNo;
    private String hashtagName;
}
