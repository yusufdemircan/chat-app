package com.yedy.chat_app.service;

import com.yedy.chat_app.entity.Message;
import com.yedy.chat_app.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(Message message) {
        messageRepository.save(message);
    }
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
    public Message findById(UUID id) {
        return messageRepository.findById(id).orElse(null);
    }

}
