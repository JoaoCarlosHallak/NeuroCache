package com.hallak.NeuroCache.services;


import com.hallak.NeuroCache.communication.EmbeddingClient;
import com.hallak.NeuroCache.dtos.EmbeddingRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingServiceImpl implements EmbeddingService{

    private final EmbeddingClient embeddingClient;

    @Autowired
    public EmbeddingServiceImpl(EmbeddingClient embeddingClient){
        this.embeddingClient = embeddingClient;
    }

    public float[] generateEmbedding(String prompt){
        return embeddingClient.generateEmbedding(
                new EmbeddingRequestDTO("nomic-embed-text", prompt))
                .embedding();
    }

}
