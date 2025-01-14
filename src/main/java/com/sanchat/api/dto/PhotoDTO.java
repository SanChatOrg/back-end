package com.sanchat.api.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PhotoDTO {
    private Long photoNo; // 사진 번호
    private String photoUrl; // 사진 링크
    private String photoName; // 사진명
    private String photoType; // 사진 타입
    private Timestamp createdAt; // 사진 등록일
    private String relatedType; // 사진 연관 타입 (USER, POST, CHAT, DOG 중 하나)
    private Long relatedEntityId; // 사진 연관 아이디
}
