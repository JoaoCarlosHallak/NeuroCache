package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.dtos.ResponseFromMixedTreatmentDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextAwareProcessorServiceImpl implements ContextAwareProcessorService {

    private final ContextMixedProcessorService contextMixedProcessorService;
    private final ContextQuestProcessorService contextQuestProcessorService;
    private final MemoryService memoryService;
    private final Logger logger = LoggerFactory.getLogger(ContextAwareProcessorServiceImpl.class);

    @Autowired
    public ContextAwareProcessorServiceImpl(ContextMixedProcessorService contextMixedProcessorService, ContextQuestProcessorService contextQuestProcessorService,MemoryService memoryService) {
        this.contextMixedProcessorService = contextMixedProcessorService;
        this.contextQuestProcessorService = contextQuestProcessorService;
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
    public ResponseFromMixedTreatmentDTO mixed(String payload) {
        return contextMixedProcessorService.treatment(payload);
    }
}
