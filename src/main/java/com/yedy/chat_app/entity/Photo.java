package com.yedy.chat_app.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "photos")
public class Photo extends BaseEntity {
    @Id
    private UUID id;
    private UUID profileId;
    private String url;
}