package com.sanchat.api.controller;

import com.sanchat.api.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    @MessageMapping("/send/{chatRoomNo}")
    @SendTo("/sub/room/{chatRoomNo}")
    public ChatMessageDTO sendMessage(ChatMessageDTO message) {
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
        return message;
    }

}
