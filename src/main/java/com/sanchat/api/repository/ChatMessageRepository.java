package com.sanchat.api.repository;

import com.sanchat.api.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomNo(Long chatRoomNo);

    @Query("db.ChatMessage.find({\"chatMessageNo\": {$exists:true}}).sort({\"chatMessageNo\":-1}).limit(1)")
    Optional<ChatMessage> getMaxChatMessageNoByChatRoomNo(Long chatRoomNo);
}
