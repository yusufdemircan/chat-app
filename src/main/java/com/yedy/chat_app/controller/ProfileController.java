package com.yedy.chat_app.controller;

import com.yedy.chat_app.dto.ProfileDto;
import com.yedy.chat_app.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("getCards")
    public List<ProfileDto> getCards(){
        return profileService.getAllProfiles();
    }
}
