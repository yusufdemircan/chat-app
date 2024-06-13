package com.yedy.chat_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "user_role")
public class UserRole extends BaseEntity{
    public String id;
    public User user;
    public Role role;
}
