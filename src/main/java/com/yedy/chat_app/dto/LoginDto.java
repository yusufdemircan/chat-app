package com.yedy.chat_app.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    String name;
    String pass;
}