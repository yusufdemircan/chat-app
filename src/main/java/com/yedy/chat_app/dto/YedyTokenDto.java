package com.yedy.chat_app.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class YedyTokenDto {
    String userId;
    Date expiration;
}