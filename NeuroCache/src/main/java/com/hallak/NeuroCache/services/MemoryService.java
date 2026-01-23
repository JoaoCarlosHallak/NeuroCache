package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;

public interface MemoryService {
    MemoryDTO saveMemory(String payload);
}
