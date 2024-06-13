package com.yedy.chat_app.entity;
import com.yedy.chat_app.enums.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile extends BaseEntity{
    @Id
    private String id;
    private String userId;
    private String name;
    private int age;
    private Gender gender;
    private String bio;
    private String profilePhotoUrl;
    private List<String> interests;
    private List<String> photos;

}