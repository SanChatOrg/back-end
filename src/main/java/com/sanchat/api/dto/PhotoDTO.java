package com.sanchat.api.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PhotoDTO {
    private Long photoNo; // 사진 번호
    private String photoUrl; // Cloudinary secure_url
    private String photoName; // Cloudinary public_id
    private String photoType; // 사진 타입
    private LocalDateTime createdAt; // 사진 등록일
    private String relatedType; // 사진 연관 타입 (USER, POST, CHAT, DOG 중 하나)
    private Long relatedEntityId; // 사진 연관 아이디
}
