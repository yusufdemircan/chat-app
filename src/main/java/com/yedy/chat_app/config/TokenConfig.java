package com.yedy.chat_app.config;


import com.yedy.chat_app.exception.YedyException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties("yedy.chat.token")
public class TokenConfig {
    String algorithm;
    String secretKey;
    ExpirationProperties adminExpiration;
    ExpirationProperties userExpiration;

    public void setAlgorithm(String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher.getInstance(algorithm);
        this.algorithm = algorithm;
    }

    public void setSecretKey(String secretKey) {
        if (secretKey == null || !List.of(16, 24, 32).contains(secretKey.length()))
            throw new YedyException("Karakter uzunluğu 16, 24 yada 32 karakter olmalıdır.");
        this.secretKey = secretKey;
    }

    @Data
    public static class ExpirationProperties {
        Long time;
        ChronoUnit type;

        public Instant getExpiration() {
            return Instant.now().plus(time, type);
        }
    }
}