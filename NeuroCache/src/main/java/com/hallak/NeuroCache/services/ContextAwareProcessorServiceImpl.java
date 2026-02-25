package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextAwareProcessorServiceImpl implements ContextAwareProcessorService {

    private final ContextQuestProcessorService contextQuestProcessorService;
    private final ChatService chatService;
    private final EmbeddingService embeddingService;
    private final ModelMapper  modelMapper;
    private final MemoryService memoryService;
    private final Logger logger = LoggerFactory.getLogger(ContextAwareProcessorServiceImpl.class);

    @Autowired
    public ContextAwareProcessorServiceImpl(ContextQuestProcessorService contextQuestProcessorService, ChatService chatService, EmbeddingService embeddingService, ModelMapper modelMapper, MemoryService memoryService) {
        this.contextQuestProcessorService = contextQuestProcessorService;
        this.chatService = chatService;
        this.embeddingService = embeddingService;
        this.modelMapper = modelMapper;
        this.memoryService = memoryService;
    }


    @Override
    public MemoryDTO info(String payload) {
        return memoryService.saveMemory(payload);


    }

    @Override
    public String quest(String payload) {
        return contextQuestProcessorService.treatment(payload);
    }

    @Override
    public String mixed(String payload) {
        return "";
    }
}
