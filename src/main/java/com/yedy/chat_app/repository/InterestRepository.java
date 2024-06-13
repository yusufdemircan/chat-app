package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface InterestRepository extends MongoRepository<Interest, String> {
}
