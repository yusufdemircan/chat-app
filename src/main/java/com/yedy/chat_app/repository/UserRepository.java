package com.yedy.chat_app.repository;
import com.yedy.chat_app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, String> {
   // User findByUsername(String username);
    User findByUsernameAndDeletedFalse(String userName);

    User findByEmailAndDeletedFalseAndEmailIsNotNull(String email);

    User findByPhoneNumberAndDeletedFalseAndPhoneNumberIsNotNull(String telNumber);
}