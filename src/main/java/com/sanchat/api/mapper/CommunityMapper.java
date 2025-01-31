package com.sanchat.api.mapper;

import com.sanchat.api.dto.CommunityDTO;
import com.sanchat.api.dto.CommunityReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    void newPost(CommunityDTO communityDTO);

    CommunityDTO getPost(Long communityNo);

    void editPost(CommunityDTO communityDTO);

    CommunityDTO getDetail(Long communityNo);

    void newReply(CommunityReplyDTO communityReplyDTO);

    List<CommunityReplyDTO> getReply(Long communityNo);

    void deleteReply(CommunityReplyDTO dto);

    void deletePost(CommunityDTO communityDTO);

    List<CommunityDTO> getAllPost();
}
