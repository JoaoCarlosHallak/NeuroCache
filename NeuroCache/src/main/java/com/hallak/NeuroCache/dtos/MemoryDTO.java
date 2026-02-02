package com.hallak.NeuroCache.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hallak.NeuroCache.entities.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemoryDTO {

    private Domain domain;
    private String content;

    @JsonIgnore
    private float[] embedding;

    private double confidence;




}
