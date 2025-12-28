package com.smanagement.service;

import com.smanagement.dto.NotificationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *  SpEL (#{__listener.xxx}) allows runtime values
 *  Spring resolves @Value before Kafka listener starts
 */
@Service
public class KafkaConsumerService {
    @Value("${kafka.psl.topic_name}")
    public String topicName;

    @Value("${kafka.psl.group-id}")
    public String groupId;
    @KafkaListener(
            topics = "${kafka.psl.topic_name}",
            groupId = "${kafka.psl.group-id}"
    )
    public void consume(NotificationEvent event) {
        System.out.println("Received Student Mesage Event: " + event);
    }
}

