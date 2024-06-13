package com.yedy.chat_app.service;
import com.yedy.chat_app.consts.BCrypto;
import com.yedy.chat_app.dto.PassChangeDto;
import com.yedy.chat_app.entity.User;
import com.yedy.chat_app.enums.ErrorMessages;
import com.yedy.chat_app.exception.Assert;
import com.yedy.chat_app.exception.YedyException;
import com.yedy.chat_app.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HelperService helperService;

    public UserService(UserRepository userRepository, HelperService helperService) {
        this.userRepository = userRepository;
        this.helperService = helperService;
    }

    public User findById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        Assert.isEmpty(user.getUsername(), ErrorMessages.USER_NAME_NULL);
        Assert.isEmpty(user.getPassword(), ErrorMessages.USER_PASS_NULL);
        Assert.isEmpty(user.getEmail(), ErrorMessages.USER_EMAIL_NULL);
        if (user.getId() == null) {
            Assert.isFalse(helperService.isPassRange(user.getPassword()), ErrorMessages.PASS_NOT_RANGE);
            Assert.notNull(findByUserName(user.getUsername()), ErrorMessages.SAME_USER_NAME);
            Assert.notNull(findByEmail(user.getEmail()), ErrorMessages.SAME_EMAIL);
            Assert.notNull(findByTelNumber(user.getPhoneNumber()), ErrorMessages.SAME_TEL_NUMBER);
            user.setPassword(BCrypto.getEncoder().encode(user.getPassword()));
        } else {
            User u = userRepository.findById(user.getId()).orElseThrow(()->new YedyException(String.valueOf(ErrorMessages.USER_NOT_FOUND)));
            if (!u.getUsername().equals(user.getUsername()))
                Assert.notNull(findByUserName(user.getUsername()), ErrorMessages.SAME_USER_NAME);
            if (!u.getEmail().equals(user.getEmail()))
                Assert.notNull(findByEmail(user.getEmail()), ErrorMessages.SAME_EMAIL);
            if (!u.getPhoneNumber().equals(user.getPhoneNumber()))
                Assert.notNull(findByTelNumber(user.getPhoneNumber()), ErrorMessages.SAME_TEL_NUMBER);

        }
        return userRepository.save(user);
    }

    public User findByUserName(String userName) {
        Assert.isEmpty(userName, ErrorMessages.USER_NAME_NULL);
        return userRepository.findByUsernameAndDeletedFalse(userName);
    }

    public void passChange(PassChangeDto pc) {
        Assert.isNull(pc.getId(), ErrorMessages.ID_NULL);
        Assert.isEmpty(pc.getOldPass(), ErrorMessages.USER_PASS_NULL);
        Assert.isEmpty(pc.getNewPass(), ErrorMessages.USER_PASS_NULL);
        Assert.isFalse(helperService.isPassRange(pc.getNewPass()), ErrorMessages.PASS_NOT_RANGE);
        User u = userRepository.findById(pc.getId()).orElseThrow(()->new YedyException("id boş olamaz."));
        Assert.isFalse(BCrypto.getEncoder().matches(pc.getOldPass(), u.getPassword()), ErrorMessages.WRONG_PASS);
        u.setPassword(BCrypto.getEncoder().encode(pc.getNewPass()));
        userRepository.save(u);
    }

    public User findByEmail(String email) {
        Assert.isNull(email, ErrorMessages.USER_EMAIL_NULL);
        return userRepository.findByEmailAndDeletedFalseAndEmailIsNotNull(email);
    }

    public User findByTelNumber(String telNumber) {
        return userRepository.findByPhoneNumberAndDeletedFalseAndPhoneNumberIsNotNull(telNumber);
    }

}