package com.hallak.NeuroCache.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallak.NeuroCache.dtos.ExtractionResultMixedDTO;
import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.dtos.ResponseFromMixedTreatmentDTO;
import com.hallak.NeuroCache.utils.SystemPrompts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextMixedProcessorServiceImpl implements ContextMixedProcessorService {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;
    private final MemoryService memoryService;
    private final ContextQuestProcessorService contextQuestProcessorService;
    private final Logger logger = LoggerFactory.getLogger(ContextMixedProcessorServiceImpl.class);

    @Autowired
    public ContextMixedProcessorServiceImpl(ChatService chatService, ObjectMapper objectMapper, MemoryService memoryService, ContextQuestProcessorService contextQuestProcessorService) {
        this.chatService = chatService;
        this.objectMapper = objectMapper;
        this.memoryService = memoryService;
        this.contextQuestProcessorService = contextQuestProcessorService;
    }


    @Override
    public ResponseFromMixedTreatmentDTO treatment(String payload) {
        String response = chatService.sendToAI(
                SystemPrompts.getMixedExtractionPrompt(payload)
        );

        ExtractionResultMixedDTO extraction;

        try {
            extraction = objectMapper.readValue(
                    response,
                    ExtractionResultMixedDTO.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON da IA", e);
        }


        MemoryDTO memoryDTO = memoryService.saveMemory(extraction.info());
        String resp = contextQuestProcessorService.treatment(extraction.question());
        logger.info("Question: " + extraction.question());
        logger.info("Info: " + extraction.question());

        return new ResponseFromMixedTreatmentDTO(memoryDTO, resp);


    }
}
