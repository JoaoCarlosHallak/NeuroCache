package com.hallak.NeuroCache.communication;


import com.hallak.NeuroCache.dtos.EmbeddingRequestDTO;
import com.hallak.NeuroCache.dtos.EmbeddingResponseDTO;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ollamaEmbeddingClient", url = "http://localhost:11434")
public interface EmbeddingClient {

    @PostMapping("/api/embeddings")
    EmbeddingResponseDTO generateEmbedding(@RequestBody EmbeddingRequestDTO embedding);
}
