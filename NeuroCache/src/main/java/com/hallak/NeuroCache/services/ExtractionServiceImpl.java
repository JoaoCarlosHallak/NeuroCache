package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.entities.Domain;
import com.hallak.NeuroCache.entities.Memory;
import com.hallak.NeuroCache.utils.SystemPrompts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

@Service
public class ExtractionServiceImpl implements ExtractionService{

    private final ChatService chatService;
    private final EmbeddingService embeddingService;

    @Autowired
    public ExtractionServiceImpl(ChatService chatService, EmbeddingService embeddingService) {
        this.chatService = chatService;
        this.embeddingService = embeddingService;

    }


    @Override
    public String assemblyMemoryObject(String payload) {
        Memory memory = new Memory();

        memory.setId("temporary");
        /*
        memory,setUserId(payload.getUserId);
         */
        memory.setUserId("temporaryUserId");

        memory.setContent(payload);

        System.out.println("Embedding >> " + Arrays.toString(embeddingService.generateEmbedding(payload)));
        memory.setEmbedding(embeddingService.generateEmbedding(payload));

        memory.setCreatedAt(Instant.now());

        System.out.println("Confidence Level >> " + Double.parseDouble(chatService.sendToAI(SystemPrompts.getAnalyzerPromptConfidence(payload))));
        memory.setConfidence(Double.parseDouble(chatService.sendToAI(SystemPrompts.getAnalyzerPromptConfidence(payload))));

        System.out.println("Domain >> " + chatService.sendToAI((SystemPrompts.getAnalyzerPromptDomain(payload))));
        memory.setDomain(Domain.valueOf(chatService.sendToAI(SystemPrompts.getAnalyzerPromptDomain(payload).toUpperCase())));

        return memory.toString();


    }
}
