package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.PhotoDTO;
import com.sanchat.api.mapper.PhotoMapper;
import com.sanchat.api.service.CloudinaryService;
import com.sanchat.api.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<PhotoDTO> getImageList(String type, Long id) {
        return photoMapper.getImageList(type, id);
    }

    @Override
    public PhotoDTO insertPhoto(MultipartFile file, String relatedType, Long relatedEntityId) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }
        Map<?, ?> uploadResult = cloudinaryService.uploadFile(file);
        String uploadedUrl = (String) uploadResult.get("secure_url");
        String publicId = (String) uploadResult.get("public_id");
        System.out.println("사진 업로드: 파일 URL = " + uploadedUrl + ", publicId = " + publicId);

        PhotoDTO photoDTO = PhotoDTO.builder()
                .photoUrl(uploadedUrl)
                .photoName(publicId)
                .photoType("IMAGE")
                .createdAt(LocalDateTime.now())
                .relatedType(relatedType)
                .relatedEntityId(relatedEntityId)
                .build();
        photoMapper.insertPhoto(photoDTO);
        return photoDTO;
    }

    @Override
    public void savePhoto(PhotoDTO photoDTO) {
        photoMapper.insertPhoto(photoDTO); // DB에 저장만 수행
        System.out.println("DB에 새 사진 저장: 파일 URL = " + photoDTO.getPhotoUrl());
    }

    @Override
    public void updatePhoto(PhotoDTO photoDTO) {
        photoMapper.updatePhoto(photoDTO); // DB 업데이트 수행
        System.out.println("DB에서 기존 사진 업데이트: 파일 URL = " + photoDTO.getPhotoUrl());
    }

    @Override
    public void deletePhoto(Long photoNo) {
        PhotoDTO photo = photoMapper.getPhotoById(photoNo); // 사진 조회
        if (photo != null) {
            try {
                cloudinaryService.destroyFile(photo.getPhotoName()); // Cloudinary에서 삭제
                photoMapper.deletePhoto(photoNo); // DB에서 삭제
                System.out.println("사진 삭제 완료: " + photo.getPhotoName());
            } catch (IOException e) {
                System.err.println("클라우디너리 사진 삭제 실패: " + e.getMessage());
            }
        } else {
            System.out.println("삭제할 사진이 없습니다 photoNo : " + photoNo);
        }
    }

    @Override
    public PhotoDTO getPhotoById(Long photoId) {
        return photoMapper.getPhotoById(photoId);
    }
}