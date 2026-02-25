package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.dtos.ResponseFromMixedTreatmentDTO;

public interface ContextAwareProcessorService {
    MemoryDTO info(String payload);
    String quest(String payload);
    ResponseFromMixedTreatmentDTO mixed(String payload);
}
