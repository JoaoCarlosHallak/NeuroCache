package com.hallak.NeuroCache.controllers;


import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.dtos.QueryResponseDTO;
import com.hallak.NeuroCache.services.ChatService;
import com.hallak.NeuroCache.services.ExtractionService;
import com.hallak.NeuroCache.services.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NeuroCacheController {
    private final ExtractionService extractionService;

    @Autowired
        public NeuroCacheController(ExtractionService extractionService) {
        this.extractionService = extractionService;

    }



    @PreAuthorize(value = "ROLE_USER")
    @GetMapping("chat")
    public ResponseEntity<QueryResponseDTO> chat(@RequestParam(defaultValue = "Diga oi") String payload) {
        return new ResponseEntity<>(extractionService.handleContextPayload(payload), HttpStatus.OK);
    }


}
