package com.yedy.chat_app.repository;
import com.yedy.chat_app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    User findByUsername(String username);
}