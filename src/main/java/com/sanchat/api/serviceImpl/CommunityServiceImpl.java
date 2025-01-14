package com.sanchat.api.serviceImpl;

import com.cloudinary.utils.ObjectUtils;
import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.mapper.CommunityMapper;
import com.sanchat.api.service.CloudinaryService;
import com.sanchat.api.service.CommunityService;
import com.sanchat.api.util.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public CommunityDTO newPost(String communityContent, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }
        Map<?, ?> uploadResult = cloudinaryService.uploadFile(file);
        String uploadedUrl = (String) uploadResult.get("secure_url");
        String publicId = (String) uploadResult.get("public_id");

        System.out.println("업로드 성공: 파일 URL = " + uploadedUrl + ", publicId = " + publicId);

        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityContent(communityContent)
                .filePath(uploadedUrl)
                .filePublicId(publicId)
                .createdAt(LocalDateTime.now())
                .build();
        communityMapper.newPost(communityDTO);
        return communityDTO;
    }

    @Override
    public CommunityDTO getPost(Long communityNo) {
        return communityMapper.getPost(communityNo);
    }

    @Override
    public CommunityDTO editPost(Long communityNo, String communityContent, MultipartFile file) throws IOException {
        CommunityDTO oldPost = communityMapper.getPost(communityNo); // 기존 게시물 조회

        if ((oldPost.getFilePath() == null || oldPost.getFilePath().isEmpty()) && (file == null || file.isEmpty())) { // 기존 사진과 새로운 사진이 모두 없는 경우 업로드 중단
            throw new IllegalArgumentException("선택된 사진이 없습니다.");
        }

        String oldPublicId = oldPost.getFilePublicId();  // 기존 이미지의 public_id 조회
        System.out.println("기존 publicId: " + oldPublicId);

        String newFilePath = oldPost.getFilePath();  // 기존 사진 URL 유지
        String newPublicId = oldPublicId;           // 기존 public_id 유지

        if (file != null && !file.isEmpty()) { // 새로운 파일이 업로드되었는지 확인
            Map<?, ?> uploadResult = cloudinaryService.uploadFile(file); // 새로 업로드된 파일 처리
            String uploadedPublicId = (String) uploadResult.get("public_id");

            if (!uploadedPublicId.equals(oldPublicId)) { // 기존 사진의 public_id와 새 public_id가 다를 경우에만 기존 파일 삭제
                if (oldPublicId != null && !oldPublicId.isEmpty()) {
                    Map<?, ?> destroyResult = cloudinaryService.destroyFile(oldPublicId); // 기존 파일 삭제
                    System.out.println("삭제 결과: " + destroyResult);
                }
                newFilePath = (String) uploadResult.get("secure_url");  // 새 파일 정보로 업데이트
                newPublicId = uploadedPublicId;
            } else {
                System.out.println("새 파일의 publicId가 기존과 동일하여 기존 파일을 유지합니다.");
            }
        } else {
            System.out.println("새 파일이 업로드되지 않아 기존 파일 유지합니다.");
        }

        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityNo(communityNo)
                .communityContent(communityContent)
                .filePath(newFilePath)
                .filePublicId(newPublicId)
                .updatedAt(LocalDateTime.now())
                .build();
        communityMapper.editPost(communityDTO);
        return communityDTO;
    }

}
