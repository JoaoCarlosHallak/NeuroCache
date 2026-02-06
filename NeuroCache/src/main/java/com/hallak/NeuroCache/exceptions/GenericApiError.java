package com.hallak.NeuroCache.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericApiError {

    private String message;
    private Integer status;
    private Instant timestamp;




}
