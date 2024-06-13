package com.yedy.chat_app.entity;
import com.yedy.chat_app.enums.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User extends BaseEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

}