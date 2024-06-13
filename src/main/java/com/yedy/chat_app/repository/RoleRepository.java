package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.Role;
import com.yedy.chat_app.enums.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByCodeAndDeletedFalse(Roles code);

}
