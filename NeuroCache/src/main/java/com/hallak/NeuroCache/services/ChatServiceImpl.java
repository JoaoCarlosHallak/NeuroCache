package com.hallak.NeuroCache.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {


    private final ChatModel chatModel;

    public ChatServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String sendToAI(String payload) {
        return chatModel.call(payload);
    }
}
