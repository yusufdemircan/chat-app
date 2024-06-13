package com.yedy.chat_app.dto;

import com.yedy.chat_app.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RegisterDto {
    String username;
    String password;
    String email;
    String phoneNumber;
    String name;
    String surname;
    Date birthday;
    Gender gender;
}