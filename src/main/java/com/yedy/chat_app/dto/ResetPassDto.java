package com.yedy.chat_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ResetPassDto {
    String userId;
    String password;
}