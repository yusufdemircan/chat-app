package com.yedy.chat_app.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "interests")
public class Interest extends BaseEntity {
    @Id
    private String id;
    private String name;
}