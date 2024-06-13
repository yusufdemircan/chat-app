package com.yedy.chat_app.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PassChangeDto implements Serializable {
    String id;
    String oldPass;
    String newPass;
}