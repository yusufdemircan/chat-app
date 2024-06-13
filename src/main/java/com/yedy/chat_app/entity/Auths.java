package com.yedy.chat_app.entity;

import com.yedy.chat_app.enums.AuthsType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "auth")
public class Auths extends BaseEntity {
    @Id
    private String id;
    private User user;
    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
    private String tokenType;
    private String scope;
    private AuthsType type;

}
