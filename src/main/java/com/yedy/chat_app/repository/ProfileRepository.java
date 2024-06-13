package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Profile findByUserId(String userId);
    List<Profile> findAllByUserIdNotContains(String userId);
}