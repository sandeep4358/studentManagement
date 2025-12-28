package com.smanagement.service;

import com.smanagement.dto.NotificationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Value("${kafka.psl.topic_name}")
    private String psl_topic_name;


    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(NotificationEvent message) {
        kafkaTemplate.send(psl_topic_name, message);
    }
}
