package com.smanagement.controller;

import com.smanagement.dto.NotificationEvent;
import com.smanagement.service.KafkaProducerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Kafka", description = "Operations about Kafka testing")
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(NotificationEvent message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka");
    }
}

