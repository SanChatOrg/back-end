package com.sanchat.api.mapper;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.CommunityLikeDTO;
import com.sanchat.api.dto.CommunityReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.List;

@Mapper
public interface CommunityMapper {
    void newPost(CommunityDTO communityDTO);

    CommunityDTO getPost(Long communityNo);

    void editPost(CommunityDTO communityDTO);

    CommunityDTO getDetail(Long communityNo);

    void newReply(CommunityReplyDTO communityReplyDTO);

    void plusReplyCnt(@Param("communityNo") Long communityNo);

    void minusReplyCnt(@Param("communityNo") Long communityNo);

    List<CommunityReplyDTO> getReply(Long communityNo);

    void deleteReply(CommunityReplyDTO dto);

    void deletePost(CommunityDTO communityDTO);

    List<CommunityDTO> getAllPost(@Param("userNo") Long userNo);

    Optional<CommunityLikeDTO> getLike(@Param("communityNo") Long communityNo, @Param("userNo") Long userNo);

    void updateLike(CommunityLikeDTO updateLike);

    void insertLike(CommunityLikeDTO newLike);

    void updateLikeCount(Long communityNo);
}