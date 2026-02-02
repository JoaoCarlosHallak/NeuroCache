package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;

public interface ContextAwareProcessorService {
    MemoryDTO info(String payload);
    String quest(String payload);
    String mixed(String payload);
}
