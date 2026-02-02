package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.QueryResponseDTO;
import com.hallak.NeuroCache.entities.Context;
import com.hallak.NeuroCache.entities.Domain;
import com.hallak.NeuroCache.entities.Memory;
import com.hallak.NeuroCache.utils.SystemPrompts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

@Service
public class ExtractionServiceImpl implements ExtractionService{

    private final ChatService chatService;
    private final ContextAwareProcessorService contextAwareProcessorService;
    private final Logger logger = LoggerFactory.getLogger(ExtractionServiceImpl.class);

    @Autowired
    public ExtractionServiceImpl(ChatService chatService, ContextAwareProcessorService contextAwareProcessorService) {
        this.chatService = chatService;
        this.contextAwareProcessorService = contextAwareProcessorService;
    }

    /* Extrair do payload o contexto. Info, Quest ou Mixed?

    Info -> ContextAwareProcessorServiceImpl.info Return response From AI
    Quest -> ContextAwareProcessorServiceImpl.quest Return response From AI
    Mixed -> ContextAwareProcessorServiceImpl.mixed Return Response From AI

     */

    @Override
    public QueryResponseDTO handleContextPayload(String payload) {
        Context contextPayload = Context.valueOf(chatService.sendToAI(SystemPrompts.getContextPayloadPrompt(payload))
                        .toUpperCase().trim());
        logger.info("Context: {}", contextPayload);
        if (contextPayload.equals(Context.INFO)){
            return new QueryResponseDTO(contextAwareProcessorService.info(payload));
        } else if (contextPayload.equals(Context.QUEST)){
            return new QueryResponseDTO(contextAwareProcessorService.quest(payload));
        } else if (contextPayload.equals(Context.MIXED)){
            return new QueryResponseDTO(contextAwareProcessorService.mixed(payload));
        } else {
            throw new IllegalArgumentException();
        }







    }
}
