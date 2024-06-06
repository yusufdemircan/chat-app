package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProfileRepository extends MongoRepository<Profile, UUID> {
    Profile findByUserId(UUID userId);
}