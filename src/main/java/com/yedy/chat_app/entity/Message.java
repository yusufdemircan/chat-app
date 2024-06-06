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
@Document(collection = "messages")
public class Message extends BaseEntity{
    @Id
    private UUID id;
    private UUID matchId;
    private UUID senderId;
    private UUID receiverId;
    private String content;
    private Date sentAt;
}