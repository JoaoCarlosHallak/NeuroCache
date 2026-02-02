package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.dtos.QueryResponseDTO;
import com.hallak.NeuroCache.entities.Memory;

public interface ExtractionService {
    QueryResponseDTO handleContextPayload(String payload);

}
