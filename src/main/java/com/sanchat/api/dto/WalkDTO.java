package com.sanchat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalkDTO {

    private int walkNo;
    private LocalTime walkTimeStart;
    private LocalTime walkTimeEnd;
    private float walkDistance;
    private LocalDate walkDate;

}
