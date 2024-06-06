package com.yedy.chat_app.service;

import com.yedy.chat_app.entity.Profile;
import com.yedy.chat_app.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile findByUserId(UUID userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile findById(UUID id) {
        return profileRepository.findById(id).orElse(null);
    }

}
