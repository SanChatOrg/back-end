package com.sanchat.api.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DogDTO {
    private Long dogNo;
    private String dogName;
    private String dogBreed;
    private Character dogGender;
    private LocalDate birthDate;
    private Boolean isNeutered;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long userNo;
}
