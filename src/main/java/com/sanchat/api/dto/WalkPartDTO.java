package com.sanchat.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalkPartDTO {

    private int walkPartNo;
    private int dogNo;
    private int walkNo;


}
