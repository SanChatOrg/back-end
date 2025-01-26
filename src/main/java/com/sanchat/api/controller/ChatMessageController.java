package com.sanchat.api.controller;

import com.sanchat.api.entity.ChatMessage;
import com.sanchat.api.enums.ChatMessageContentEnum;
import com.sanchat.api.enums.ChatMessageEnum;
import com.sanchat.api.repository.ChatMessageRepository;
import com.sanchat.api.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/send/{chatRoomNo}")
    @SendTo("/sub/room/{chatRoomNo}")
    public ChatMessage sendMessage(ChatMessage message) {
        System.out.println("Sending message: " + message);
        return chatMessageService.checkMessage(message);
    }

    // chatting list 반환
    @GetMapping("/chat/{chatRoomNo}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long chatRoomNo) {
        return ResponseEntity.ok().body(chatMessageService.findByChatRoomNo(chatRoomNo));
    }
}
