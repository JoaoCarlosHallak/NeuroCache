package com.hallak.NeuroCache.repositories;

import com.hallak.NeuroCache.entities.Memory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemoryRepository extends MongoRepository<Memory, String> {
}
