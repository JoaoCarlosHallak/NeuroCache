package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.entities.Memory;
import com.hallak.NeuroCache.repositories.MemoryRepository;
import com.hallak.NeuroCache.utils.SystemPrompts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContextQuestProcessorServiceImpl implements ContextQuestProcessorService {

    private final MemoryService memoryService;
    private final UserService userService;
    private final EmbeddingService embeddingService;
    private final Logger logger = LoggerFactory.getLogger(ContextQuestProcessorServiceImpl.class);
    private final ChatService chatService;


    @Autowired
    public ContextQuestProcessorServiceImpl(MemoryService memoryService, UserService userService, EmbeddingService embeddingService, ChatService chatService) {
        this.memoryService = memoryService;
        this.userService = userService;
        this.embeddingService = embeddingService;
        this.chatService = chatService;
    }


    public String treatment(String payload) {
        List<Memory> memoryList = memoryService.findRelevantMemories(userService.getAuthenticatedUser().getId(), embeddingService.generateEmbedding(payload));
        logger.info("Do treatment (RDM): {}", chatService.sendToAI(SystemPrompts.getResponderQuestPrompt(payload, memoryList)));
        return chatService.sendToAI(SystemPrompts.getResponderQuestPrompt(payload, memoryList));



    }

}
