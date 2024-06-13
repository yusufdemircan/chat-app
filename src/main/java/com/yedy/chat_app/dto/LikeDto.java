package com.yedy.chat_app.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class LikeDto {
    private String id;
    private ProfileDto likeProfile;
    private boolean mutual; // Eşleşme durumu
    private Date likedAt;
}
