package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.entities.Domain;
import com.hallak.NeuroCache.entities.Memory;
import com.hallak.NeuroCache.entities.MemoryScore;
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
import java.util.List;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository  memoryRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(MemoryServiceImpl.class);
    private final EmbeddingService embeddingService;
    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public MemoryServiceImpl(MemoryRepository memoryRepository, ModelMapper modelMapper, EmbeddingService embeddingService, ChatService chatService, UserService userService) {
        this.memoryRepository = memoryRepository;
        this.modelMapper = modelMapper;
        this.embeddingService = embeddingService;
        this.chatService = chatService;
        this.userService = userService;
    }


    public MemoryDTO saveMemory(String payload) {
        try {
            Memory memory = new Memory();

            memory.setUserId(userService.getAuthenticatedUser().getId());

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

    public List<Memory> findRelevantMemories(String userId, float[] questionEmbedding) {

        List<Memory> memories = memoryRepository.findByUserId(userId);

        return memories.stream()
                .map(memory -> {
                    double similarity = cosineSimilarity(
                            questionEmbedding,
                            memory.getEmbedding()
                    );

                    double finalScore = similarity * memory.getConfidence();

                    return new MemoryScore(memory, finalScore);
                })
                .sorted((a, b) -> Double.compare(b.score(), a.score()))
                .limit(5)
                .map(MemoryScore::memory)
                .toList();
    }

    private double cosineSimilarity(float[] v1, float[] v2) {
        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < v1.length; i++) {
            dot += v1[i] * v2[i];
            normA += v1[i] * v1[i];
            normB += v2[i] * v2[i];
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
