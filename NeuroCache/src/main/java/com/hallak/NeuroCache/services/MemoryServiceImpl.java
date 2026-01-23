package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.repositories.MemoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository  memoryRepository;
    private final ModelMapper modelMapper;
    private final ExtractionService extractionService;
    private final Logger logger = LoggerFactory.getLogger(MemoryServiceImpl.class);

    @Autowired
    public MemoryServiceImpl(MemoryRepository memoryRepository,  ModelMapper modelMapper, ExtractionService extractionService) {
        this.memoryRepository = memoryRepository;
        this.modelMapper = modelMapper;
        this.extractionService = extractionService;
    }


    public MemoryDTO saveMemory(String payload) {
        try {
            MemoryDTO memoryDTO = modelMapper.map
                    (memoryRepository.save
                            (extractionService.assemblyMemoryObject(payload)), MemoryDTO.class);
            logger.info("MemoryDTO saved successfully");
            return memoryDTO;
        } catch (Exception e) {
            logger.error("Error to save Memory: {}", e.getMessage());
            throw e;
        }





    }


}
