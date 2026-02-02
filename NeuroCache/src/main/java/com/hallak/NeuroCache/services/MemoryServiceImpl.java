package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.entities.Domain;
import com.hallak.NeuroCache.entities.Memory;
import com.hallak.NeuroCache.repositories.MemoryRepository;
import com.hallak.NeuroCache.utils.SystemPrompts;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;
import java.time.Instant;
import java.util.Arrays;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository  memoryRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(MemoryServiceImpl.class);
    private final EmbeddingService embeddingService;
    private final ChatService chatService;

    @Autowired
    public MemoryServiceImpl(MemoryRepository memoryRepository, ModelMapper modelMapper, EmbeddingService embeddingService, ChatService chatService) {
        this.memoryRepository = memoryRepository;
        this.modelMapper = modelMapper;
        this.embeddingService = embeddingService;
        this.chatService = chatService;
    }


    public MemoryDTO saveMemory(String payload) {
        try {
            Memory memory = new Memory();

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
            memory.setDomain(Domain.valueOf(chatService.sendToAI(SystemPrompts.getAnalyzerPromptDomain(payload)).trim().toUpperCase()));

            memoryRepository.save(memory);
            logger.info("Memory saved successfully {}", memory);
            return modelMapper.map(memory, MemoryDTO.class);
        } catch (Exception e) {
            logger.error("Error to save Memory: {}", e.getMessage());
            throw e;
        }





    }


}
