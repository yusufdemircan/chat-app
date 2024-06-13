package com.yedy.chat_app.entity;

import com.yedy.chat_app.enums.Roles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "role")
public class Role extends BaseEntity{
    private String id;
    private String name;
    private Roles code;
    private String description;

}
