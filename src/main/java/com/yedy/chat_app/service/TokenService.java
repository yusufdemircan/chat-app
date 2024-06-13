package com.yedy.chat_app.service;

import com.yedy.chat_app.config.TokenConfig;
import com.yedy.chat_app.dto.YedyTokenDto;
import com.yedy.chat_app.enums.ErrorMessages;
import com.yedy.chat_app.enums.Roles;
import com.yedy.chat_app.exception.Assert;
import com.yedy.chat_app.exception.YedyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService  {
    private final TokenConfig tokenConfig;
    private final HelperService helperService;
    private final UserRoleService userRoleService;

    public String generateToken(String id) {
        boolean admin = userRoleService.getRoles(id).contains(Roles.ADMIN.toString());
        Instant expiration = (admin ? tokenConfig.getAdminExpiration() : tokenConfig.getUserExpiration()).getExpiration();
        Date exp = Date.from(expiration);
        YedyTokenDto token = new YedyTokenDto();
        token.setUserId(id);
        token.setExpiration(exp);
        String data = helperService.toJson(token);
        try {
            return encodeToken(data);
        } catch (Exception e) {
            throw new YedyException(e);
        }
    }

    public YedyTokenDto validateToken(String token) {
        YedyTokenDto claims;
        try {
            claims = decodeToken(token);
        } catch (Exception e) {
            throw new YedyException(e);
        }
        Assert.isNull(claims.getExpiration(), ErrorMessages.TOKEN_EXPIRED_NULL);
        Assert.isTrue(claims.getExpiration().before(new Date()), ErrorMessages.TOKEN_EXPIRED);
        return claims;
    }

    private String encodeToken(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(tokenConfig.getAlgorithm());
        SecretKeySpec secretKeySpec = new SecretKeySpec(tokenConfig.getSecretKey().getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] ivBytes = cipher.getIV();
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        byte[] combined = new byte[ivBytes.length + encryptedBytes.length];
        System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
        System.arraycopy(encryptedBytes, 0, combined, ivBytes.length, encryptedBytes.length);
        return Base64.getEncoder().encodeToString(combined);
    }

    private YedyTokenDto decodeToken(String token) throws Exception {
        byte[] combined = Base64.getDecoder().decode(token);
        byte[] ivBytes = Arrays.copyOfRange(combined, 0, 16);
        byte[] encryptedBytes = Arrays.copyOfRange(combined, 16, combined.length);
        Cipher cipher = Cipher.getInstance(tokenConfig.getAlgorithm());
        SecretKeySpec secretKeySpec = new SecretKeySpec(tokenConfig.getSecretKey().getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decodedText = new String(decryptedBytes);
        return helperService.fromJson(decodedText, YedyTokenDto.class);
    }
}