package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PhotoRepository extends MongoRepository<Photo, UUID> {
    List<Photo> findByProfileId(UUID profileId);
}
