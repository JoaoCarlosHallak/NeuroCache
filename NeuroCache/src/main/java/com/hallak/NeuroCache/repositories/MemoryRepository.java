package com.hallak.NeuroCache.repositories;

import com.hallak.NeuroCache.entities.Memory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemoryRepository extends MongoRepository<Memory, String> {
    List<Memory> findByUserId(String userId);

}
