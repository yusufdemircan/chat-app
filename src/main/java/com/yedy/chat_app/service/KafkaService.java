package com.yedy.chat_app.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "chat_messages";

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    @KafkaListener(topics = TOPIC, groupId = "chat-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}