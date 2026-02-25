package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.ResponseFromMixedTreatmentDTO;

public interface ContextMixedProcessorService {
    ResponseFromMixedTreatmentDTO treatment(String payload);
}
