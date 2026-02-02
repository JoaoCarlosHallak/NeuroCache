package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContextAwareProcessorServiceImpl implements ContextAwareProcessorService {

    private final ChatService chatService;
    private final EmbeddingService embeddingService;
    private final ModelMapper  modelMapper;
    private final MemoryService memoryService;
    private final Logger logger = LoggerFactory.getLogger(ContextAwareProcessorServiceImpl.class);

    public ContextAwareProcessorServiceImpl(ChatService chatService, EmbeddingService embeddingService, ModelMapper modelMapper, MemoryService memoryService) {
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
        return "";
    }

    @Override
    public String mixed(String payload) {
        return "";
    }
}
