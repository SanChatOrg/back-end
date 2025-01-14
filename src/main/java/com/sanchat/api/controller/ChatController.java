package com.sanchat.api.controller;

import com.sanchat.api.dto.ChatRoomDTO;
import com.sanchat.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    // 채팅방 목록 가져오기
    // /api/chat/room/list/유저번호
    @GetMapping("/room/list/{userNo}")
    public ResponseEntity<List<ChatRoomDTO>> getChatRoomList(@PathVariable Long userNo) {
        return ResponseEntity.ok().body(chatRoomService.getAllChatRoomsByUserNo(userNo));
    }

    // 채팅방 생성
    @PostMapping("/room/add")
    public ResponseEntity<Void> addChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        int result = chatRoomService.saveChatRoom(chatRoomDTO);
        if (result == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // 채팅방 수정
    @PostMapping("/room/edit")
    public ResponseEntity<Void> editChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        int result = chatRoomService.saveChatRoom(chatRoomDTO);
        if (result == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
