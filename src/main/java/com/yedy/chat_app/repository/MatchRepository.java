package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends MongoRepository<Match, String> {
    List<Match> findByUserId1OrUserId2(String userId1, String userId2);
    void deleteByUserId1AndUserId2(String userId1,String userId2);
}
