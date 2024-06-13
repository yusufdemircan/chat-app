package com.yedy.chat_app.service;

import com.yedy.chat_app.consts.RegexConst;
import com.yedy.chat_app.utils.CustomDateSerializer;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class HelperService {

    public boolean isPassRange(String pass) {
        return pass != null && pass.length() >= 8 && pass.length() <= 36;
    }


    public <T> T copyClass(Object from, Class<T> to) {
        return fromJson(toJson(from), to);
    }


    public <T> T copyClass(Object from, Type to) {
        return fromJson(toJson(from), to);
    }

    public String toJson(Object src) {
        Gson g = getGson();
        return g.toJson(src);
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        Gson g = getGson();
        return g.fromJson(json, classOfT);
    }

    public <T> T fromJson(String json, Type typeOfT) {
        Gson g = getGson();
        return g.fromJson(json, typeOfT);
    }


    public String randomPassGenerator() {
        String allowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int length = random.nextInt(8) + 8;
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(index);
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    public Boolean mathRegexUserName(String userName) {
        return userName.matches(RegexConst.userNameRegex);
    }

    public Boolean mathRegexPassword(String password) {
        return password.matches(RegexConst.passwordRegex);
    }

    public Boolean mathRegexEmail(String email) {
        return email.matches(RegexConst.emailRegex);
    }

    public Boolean mathRegexTelNumber(String telNumber) {
        return telNumber.matches(RegexConst.telNumberRegex);
    }

    private Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new CustomDateSerializer())
                .create();
    }
}
