package com.yedy.chat_app.repository;
import com.yedy.chat_app.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends MongoRepository<Message, UUID> {
    List<Message> findByMatchId(UUID matchId);

}