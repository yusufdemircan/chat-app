package com.yedy.chat_app.controller;

import com.yedy.chat_app.entity.Message;
import com.yedy.chat_app.service.KafkaService;
import com.yedy.chat_app.service.MessageService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {
    private final KafkaService kafkaService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DirectExchange exchange;
    private final RabbitTemplate rabbitTemplate;
    private final MessageService messageService;
    public ChatController(KafkaService kafkaService, RedisTemplate<String, Object> redisTemplate, DirectExchange exchange, RabbitTemplate rabbitTemplate, MessageService messageService) {
        this.kafkaService = kafkaService;
        this.redisTemplate = redisTemplate;
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        message.setSentAt(new Date());

        // Kafka'ya mesaj gönderme
        kafkaService.sendMessage(message.getContent());
        // Redis'e mesaj kaydetme
        redisTemplate.opsForList().rightPush("chat_messages", message);

        return message;
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void receiveQueue(Message message) {
        messageService.save(message);
        rabbitTemplate.convertAndSend(exchange.getName(),"secondRoute",message);
    }

    @RabbitListener(queues = "secondStepQueue")
    public void receiveSecondQueue(Message message) {
        kafkaService.sendMessage(message.getContent());
    }
}