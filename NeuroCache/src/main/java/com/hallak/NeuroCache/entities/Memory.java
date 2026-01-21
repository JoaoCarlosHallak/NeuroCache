package com.hallak.NeuroCache.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "memories")
public class Memory {

    @Id
    private String id;

    private String userId;

    private Domain domain;

    private String content;

    private float[] embedding;

    private double confidence;

    private Instant createdAt;


}
