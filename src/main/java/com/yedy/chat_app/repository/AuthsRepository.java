package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Auths;
import com.yedy.chat_app.enums.AuthsType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AuthsRepository extends MongoRepository<Auths, String> {
    Auths findByDeletedFalseAndTypeAndUser_Id(AuthsType type, String id);
}