package com.yedy.chat_app.controller;

import com.yedy.chat_app.consts.BCrypto;
import com.yedy.chat_app.dto.ForgotDto;
import com.yedy.chat_app.dto.LoginDto;
import com.yedy.chat_app.dto.RegisterDto;
import com.yedy.chat_app.dto.ResetPassDto;
import com.yedy.chat_app.entity.User;
import com.yedy.chat_app.enums.ErrorMessages;
import com.yedy.chat_app.exception.Assert;
import com.yedy.chat_app.service.HelperService;
import com.yedy.chat_app.service.TokenService;
import com.yedy.chat_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;
    private final HelperService helperService;

    @GetMapping(value = "checkToken")
    public void checkToken() {
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody LoginDto login) {
        Assert.isEmpty(login.getPass(), ErrorMessages.USER_PASS_NULL);
        User user = userService.findByUserName(login.getName());
        if (user == null)
            user = userService.findByEmail(login.getName());
        if (user == null)
            user = userService.findByTelNumber(login.getName());
        Assert.isNull(user, ErrorMessages.USER_NOT_FOUND);
        Assert.isFalse(BCrypto.getEncoder().matches(login.getPass(), user.getPassword()), ErrorMessages.WRONG_PASS);
        //Assert.isFalse(user.isComfirmMail(), ErrorMessages.MAIL_NOT_ACCEPTED);
        String token = tokenService.generateToken(user.getId());

        return token;
    }


    @Transactional
    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterDto register) {
        Assert.isFalse(helperService.mathRegexUserName(register.getUsername()), ErrorMessages.USER_NAME_NOT_MATCH_REGEX);
        Assert.isFalse(helperService.mathRegexPassword(register.getPassword()), ErrorMessages.USER_PASS_NOT_MATCH_REGEX);
        Assert.isFalse(helperService.mathRegexEmail(register.getEmail()), ErrorMessages.USER_EMAIL_NOT_MATCH_REGEX);
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setEmail(register.getEmail());
        if (register.getPhoneNumber() != null) {
            Assert.isFalse(helperService.mathRegexTelNumber(register.getPhoneNumber()), ErrorMessages.USER_TEL_NUMBER_NOT_MATCH_REGEX);
            user.setPhoneNumber(register.getPhoneNumber());
        }
        User savedUser = userService.save(user);
       /* Pr userInfo = new UserInfo();
        userInfo.setUser(savedUser);
        userInfo.setName(register.getName());
        userInfo.setSurname(register.getSurname());
        userInfo.setBirthday(register.getBirthday());
        userInfo.setGender(register.getGender());
        userInfo.setCoin(1000L);
        userInfo.setToken(1000L);
        userInfoService.save(userInfo);*/

        return ResponseEntity.ok("bşarılı kayıt.");
    }

    @Transactional
    @PostMapping("forgotPass")
    public ResponseEntity<?> forgotPass(@RequestBody ForgotDto forgotDto) {
        Assert.isNull(forgotDto, ErrorMessages.REQUEST_NOT_NULL);
        User user = userService.findByUserName(forgotDto.getName());
        if (user == null)
            user = userService.findByEmail(forgotDto.getName());
        if (user == null)
            user = userService.findByTelNumber(forgotDto.getName());
        Assert.isNull(user, ErrorMessages.USER_NOT_FOUND);
        /*EmailDetailsDto emailDetailsDto = new EmailDetailsDto();
        emailDetailsDto.setRecipient(user.getEmail());
        emailDetailsDto.setSubject("Şifre Sıfırlama");
        emailDetailsDto.setMsgBody(user.getUserName());
        emailService.sendForgotPassWithTemplate(emailDetailsDto, user.getId());*/
        return ResponseEntity.ok("mail gönderildi");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassDto resetPassDto) {
        Assert.isNull(resetPassDto, ErrorMessages.REQUEST_NOT_NULL);
        Assert.isEmpty(resetPassDto.getPassword(), ErrorMessages.USER_PASS_NULL);
        Assert.isFalse(helperService.isPassRange(resetPassDto.getPassword()), ErrorMessages.PASS_NOT_RANGE);
        Assert.isFalse(helperService.mathRegexPassword(resetPassDto.getPassword()), ErrorMessages.USER_PASS_NOT_MATCH_REGEX);
        User user = userService.findById(resetPassDto.getUserId());
        if (user != null) {
            user.setPassword(BCrypto.getEncoder().encode(resetPassDto.getPassword()));
        }
        return ResponseEntity.ok("başarılı");

    }
}