package com.yedy.chat_app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
public class ProfileDto {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String bio;
    private String profilePhotoUrl;
    private List<String> interests;
    private List<String> photos;
}
