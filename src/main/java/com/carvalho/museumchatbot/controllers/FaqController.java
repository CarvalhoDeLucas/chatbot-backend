package com.carvalho.museumchatbot.controllers;

import com.carvalho.museumchatbot.dtos.MessageRequestDTO;
import com.carvalho.museumchatbot.dtos.MessageResponseDTO;
import com.carvalho.museumchatbot.services.FaqService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class FaqController {

    final private FaqService faqService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> answerQuestion(@RequestBody MessageRequestDTO messageRequest){
        String answer = this.faqService.getAnswer(messageRequest.message());
        MessageResponseDTO response = new MessageResponseDTO(answer);
        return ResponseEntity.ok(response);
    }

}
