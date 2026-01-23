package com.hallak.NeuroCache.controllers;


import com.hallak.NeuroCache.dtos.MemoryDTO;
import com.hallak.NeuroCache.services.ChatService;
import com.hallak.NeuroCache.services.ExtractionService;
import com.hallak.NeuroCache.services.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NeuroCacheController {
    private final MemoryService memoryService;

    @Autowired
        public NeuroCacheController(MemoryService memoryService) {
        this.memoryService = memoryService;

    }



    @GetMapping("chat")
    public ResponseEntity<MemoryDTO> chat(@RequestParam(defaultValue = "Conte uma piada") String payload) {
        return new ResponseEntity<>(memoryService.saveMemory(payload), HttpStatus.OK);
    }


}
