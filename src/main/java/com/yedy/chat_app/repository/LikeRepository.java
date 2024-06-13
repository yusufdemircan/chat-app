package com.yedy.chat_app.repository;
import com.yedy.chat_app.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends MongoRepository<Like, String> {
    Like findByLikerUserIdAndLikedProfileId(String userId, String profileId);
    List<Like> findByMutual(boolean isMutual);
    List<Like> findByLikerUserId(String userId);
    void deleteByLikerUserIdAndLikedProfileId(String userId, String profileId);
}