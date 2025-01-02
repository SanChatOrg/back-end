package com.sanchat.api.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WalkDTO {
    private Long walkNo;
    private Time walkTime;
    private Double walkDistance;
    private LocalDate walkDate;
}
