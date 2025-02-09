package com.sanchat.api.service;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.CommunityLikeDTO;
import com.sanchat.api.dto.CommunityReplyDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommunityService {
    CommunityDTO newPost(String communityContent, List<MultipartFile> files) throws IOException;

    CommunityDTO getPost(Long communityNo);

    CommunityDTO editPost(Long communityNo, String communityContent, List<MultipartFile> files, List<Long> photoIdsToDelete) throws IOException;

    CommunityDTO getDetail(Long communityNo);

    CommunityReplyDTO newReply(Long communityNo, String replyContent, Long userNo, Long replyParentNo);

    List<CommunityReplyDTO> getReply(Long communityNo);

    CommunityReplyDTO deleteReply(Long communityNo, Long replyNo);

    CommunityDTO deletePost(Long communityNo);

    List<CommunityDTO> getAllPost(Long userNo);

    CommunityLikeDTO likePost(Long communityNo, Long userNo);


}
