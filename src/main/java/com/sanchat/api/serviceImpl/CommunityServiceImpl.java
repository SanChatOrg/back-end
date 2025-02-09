package com.sanchat.api.serviceImpl;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.CommunityLikeDTO;
import com.sanchat.api.dto.CommunityReplyDTO;
import com.sanchat.api.dto.PhotoDTO;
import com.sanchat.api.mapper.CommunityMapper;
import com.sanchat.api.mapper.PhotoMapper;
import com.sanchat.api.service.CloudinaryService;
import com.sanchat.api.service.CommunityService;
import com.sanchat.api.service.PhotoService;
import com.sanchat.api.util.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public CommunityDTO newPost(String communityContent, List<MultipartFile> files) throws IOException {
        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityContent(communityContent)
                .createdAt(LocalDateTime.now())
                .communityViewCount(0L)
                .communityLikeCount(0L)
                .communityReplyCount(0L)
                .communityDeleted("n")
                .userNo(1L) // 테스트용도
                .build();
        communityMapper.newPost(communityDTO);

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    photoService.insertPhoto(file, "COMMUNITY", communityDTO.getCommunityNo());
                }
            }
        }
        System.out.println("새 글 업로드 완료");

        return communityDTO;
    }

    @Override
    public CommunityDTO getPost(Long communityNo) {
        CommunityDTO communityDTO = communityMapper.getPost(communityNo);
        if (communityDTO == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
        }
        List<PhotoDTO> photoList = photoService.getImageList("COMMUNITY", communityNo);
        communityDTO.setPhotoList(photoList);
        return communityDTO;
    }

    @Override
    public CommunityDTO editPost(Long communityNo, String communityContent, List<MultipartFile> files, List<Long> photoIdsToDelete) throws IOException {
        CommunityDTO oldPost = communityMapper.getPost(communityNo);
        if (oldPost == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
        }

        if (photoIdsToDelete != null && !photoIdsToDelete.isEmpty()) {
            for (Long photoId : photoIdsToDelete) {
                PhotoDTO photo = photoService.getPhotoById(photoId);
                if (photo != null) {
                    cloudinaryService.destroyFile(photo.getPhotoName()); // Cloudinary에서 삭제
                    photoService.deletePhoto(photoId); // DB에서 삭제
                }
            }
            System.out.println("사용자가 첨부 취소한 기존 사진 삭제 완료");
        }

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    photoService.insertPhoto(file, "COMMUNITY", communityNo);
                }
            }
        }

        oldPost.setCommunityContent(communityContent);
        oldPost.setUpdatedAt(LocalDateTime.now());
        communityMapper.editPost(oldPost);
        System.out.println("본문 수정 완료: " + communityContent);

        System.out.println("게시글 수정 완료 communityNo : " + communityNo);
        return oldPost;
    }

    @Override
    public CommunityDTO getDetail(Long communityNo) {
        CommunityDTO communityDTO = communityMapper.getDetail(communityNo);
        if (communityDTO == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
        }
        List<PhotoDTO> photoList = photoService.getImageList("COMMUNITY", communityNo);
        communityDTO.setPhotoList(photoList);
        return communityDTO;
    }

    @Override
    public CommunityReplyDTO newReply(Long communityNo, String replyContent, Long userNo, Long replyParentNo) {
        CommunityReplyDTO communityReplyDTO = CommunityReplyDTO.builder()
                .replyParentNo(replyParentNo)
                .replyContent(replyContent)
                .createdAt(LocalDateTime.now())
                .replyDeleted("n")
                .communityNo(communityNo)
                .userNo(2L) // 테스트 용도
                .build();
        communityMapper.newReply(communityReplyDTO);

        communityMapper.plusReplyCnt(communityNo);

        System.out.println(communityNo + "번 게시글 작성자" + userNo + " 댓글 업로드 완료: " + replyContent);
        return communityReplyDTO;
    }

    @Override
    public List<CommunityReplyDTO> getReply(Long communityNo) {
        List<CommunityReplyDTO> replyList = communityMapper.getReply(communityNo);
        if (replyList.isEmpty()) {
            return Collections.emptyList();
        }
        return replyList;
    }

    @Override
    public CommunityReplyDTO deleteReply(Long communityNo, Long replyNo) {
        CommunityReplyDTO communityReplyDTO = CommunityReplyDTO.builder()
                .replyNo(replyNo)
                .communityNo(communityNo)
                .deletedAt(LocalDateTime.now())
                .build();
        communityMapper.deleteReply(communityReplyDTO);

        communityMapper.minusReplyCnt(communityNo);

        System.out.println(communityNo + "번 게시글 " + replyNo + "번 댓글 삭제 완료");
        return communityReplyDTO;
    }

    @Override
    public CommunityDTO deletePost(Long communityNo) {
        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityNo(communityNo)
                .updatedAt(LocalDateTime.now())
                .communityDeleted("y")
                .build();
        communityMapper.deletePost(communityDTO);
        return communityDTO;
    }

    @Override
    public List<CommunityDTO> getAllPost(Long userNo) {
        List<CommunityDTO> communityList = communityMapper.getAllPost(userNo);
        communityList.forEach(dto -> {
            List<PhotoDTO> photoList = photoService.getImageList("COMMUNITY", dto.getCommunityNo());
            dto.setPhotoList(photoList);
        });
        return communityList;
    }


    @Override
    public CommunityLikeDTO likePost(Long communityNo, Long userNo) {
        Optional<CommunityLikeDTO> existingLike = communityMapper.getLike(communityNo, userNo);
        CommunityLikeDTO result;

        if (existingLike.isPresent()) {
            CommunityLikeDTO currentLike = existingLike.get();
            String newLikeStatus = "y".equals(currentLike.getIsLiked()) ? "n" : "y";

            CommunityLikeDTO updateLike = CommunityLikeDTO.builder()
                    .likeNo(currentLike.getLikeNo())
                    .isLiked(newLikeStatus)
                    .userNo(userNo)
                    .communityNo(communityNo)
                    .build();
            communityMapper.updateLike(updateLike);
            result = updateLike;
        } else {
            CommunityLikeDTO newLike = CommunityLikeDTO.builder()
                    .createdAt(LocalDateTime.now())
                    .isLiked("y")
                    .userNo(2L) // 테스트 용도
                    .communityNo(communityNo)
                    .build();
            communityMapper.insertLike(newLike);
            result = newLike;
        }

        communityMapper.updateLikeCount(communityNo);

        return result;
    }

}
