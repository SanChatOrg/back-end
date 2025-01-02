package com.sanchat.api;

import com.sanchat.api.dto.ChatRoomDTO;
import com.sanchat.api.mapper.ChatRoomMapper;
import com.sanchat.api.service.ChatRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChatRoomTest {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Test
    public void chatRoomTest01() {
//        List<ChatRoomDTO> list = chatRoomService.getAllChatRoomsByUserNo(1L);
//        System.out.println(list.toString());
    }

    @Test
    public void chatRoomTest02() {
//        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder()
//                .chatRoomNo(4L)
//                .chatRoomName("채팅방 2 수정테스트")
//                .build();
//        int result = chatRoomMapper.save(chatRoomDTO);
//        System.out.println(result);
    }
}
