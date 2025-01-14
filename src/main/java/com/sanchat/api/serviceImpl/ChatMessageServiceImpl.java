package com.sanchat.api.serviceImpl;

import com.sanchat.api.mapper.ChatMessageMapper;
import com.sanchat.api.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("chatMessageService")
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageMapper chatMessageMapper;
}
