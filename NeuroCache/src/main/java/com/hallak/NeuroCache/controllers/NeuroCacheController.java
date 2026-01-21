package com.hallak.NeuroCache.controllers;


import com.hallak.NeuroCache.services.ChatService;
import com.hallak.NeuroCache.services.ExtractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NeuroCacheController {


    private final ChatService chatService;

    public NeuroCacheController(ChatService chatService) {
        this.chatService = chatService;
    }



    @GetMapping("chat")
    public ResponseEntity<?> chat(@RequestParam(defaultValue = "Conte uma piada") String payload) {
        return new ResponseEntity<>(chatService.sendToAI(payload), HttpStatus.OK);
    }
}
