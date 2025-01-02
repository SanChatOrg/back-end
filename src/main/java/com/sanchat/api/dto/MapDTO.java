package com.sanchat.api.dto;

import lombok.*;
import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class MapDTO {

    private Long id;
    private Double latitude;
    private Double longitude;
    private Point point;

}
