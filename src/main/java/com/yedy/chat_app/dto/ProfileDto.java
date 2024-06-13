package com.yedy.chat_app.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ProfileDto {
    private String id;
    private String name;
    private String surname;
    private Date birthday;
    private String gender;
    private String bio;
    private String profilePhotoUrl;
    private List<String> interests;
    private List<String> photos;
}
