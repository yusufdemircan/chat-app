package com.yedy.chat_app.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "likes")
public class Like {
    @Id
    private UUID id;
    private UUID likerUserId;
    private UUID likedProfileId;
    private boolean mutual; // Eşleşme durumu
    private Date likedAt;

}