package com.sanchat.api.serviceImpl;

import com.sanchat.api.entity.ChatMessage;
import com.sanchat.api.repository.ChatMessageRepository;
import com.sanchat.api.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("chatMessageService")
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage checkMessage(ChatMessage message) {
        checkMessageType(message);
        setChatMessageNo(message);

        mongoTemplate.insert(message);
        return message;
    }

    @Override
    public List<ChatMessage> findByChatRoomNo(Long chatRoomNo) {
        return chatMessageRepository.findByChatRoomNo(chatRoomNo);
    }

    private void checkMessageType(ChatMessage message) {
        switch (message.getMessageType()) {
            case ENTER:
                message.setMessageContent(message.getUserNo() + "님이 입장하였습니다.");
                break;
            case EXIT:
                message.setMessageContent(message.getUserNo() + "님이 나갔습니다.");
                break;
            case CHAT:
                break;
            default:
                log.error("메세지 형식이 잘못됨.");
        }
        log.info(message.toString());
    }

    private void setChatMessageNo(ChatMessage message) {
        if (message.getChatMessageNo() != null) return;

        Query query = new Query();
        query.addCriteria(Criteria.where("chatMessageNo").exists(true).ne(null));
        query.with(Sort.by(Sort.Direction.DESC, "chatMessageNo"));
        query.limit(1);
        query.fields().include("chatMessageNo");

        // 쿼리 실행하여 최대 채팅 메시지 조회
        ChatMessage maxChatMessage = mongoTemplate.findOne(query, ChatMessage.class);

        // 최대 채팅 메시지 번호 설정 (값이 없을 경우 기본값 1L)
        Long maxChatMessageNo = (maxChatMessage == null)
                ? 1L
                : maxChatMessage.getChatMessageNo() + 1;
        message.setChatMessageNo(maxChatMessageNo);
    }
}
