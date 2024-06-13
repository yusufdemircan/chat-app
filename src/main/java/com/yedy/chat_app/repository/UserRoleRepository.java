package com.yedy.chat_app.repository;

import com.yedy.chat_app.entity.User;
import com.yedy.chat_app.entity.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    List<UserRole> findByUserAndDeletedFalse(User user);

    @Query("""
            select r.code
            from User u
                     inner join UserRole ur on ur.user.id = u.id
                     inner join Role r on r.id = ur.role.id
            where u.id = :userId
              and u.deleted = false
              and ur.deleted = false
              and r.deleted = false""")
    List<String> getRoles(String userId);
}
