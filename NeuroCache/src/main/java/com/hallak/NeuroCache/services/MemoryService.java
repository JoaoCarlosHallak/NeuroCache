package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.entities.Memory;

import java.util.List;

public interface MemoryService {
    MemoryDTO saveMemory(String payload);
    List<Memory> findRelevantMemories(String userId, float[] questionEmbedding);
}
