package com.yedy.chat_app.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "matches")
public class Match extends BaseEntity {
    @Id
    private UUID id;
    private UUID userId1;
    private UUID userId2;
    private Date matchedAt;
}