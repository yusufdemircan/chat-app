package com.yedy.chat_app.consts;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCrypto {
    private static PasswordEncoder encoder = null;

    public static PasswordEncoder getEncoder() {
        if (encoder == null)
            encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}