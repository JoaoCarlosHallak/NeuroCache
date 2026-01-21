package com.hallak.NeuroCache.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractionServiceImpl implements ExtractionService{

    private final ChatModel chatModel;

    @Autowired
    public ExtractionServiceImpl(ChatModel chatModel){
        this.chatModel = chatModel;
    }


    @Override
    public String payloadExtraction(String payload) {
        return chatModel.call(payload);





    }
}
