package com.yedy.chat_app.service;

import com.yedy.chat_app.consts.UserContext;
import com.yedy.chat_app.dto.ProfileDto;
import com.yedy.chat_app.entity.Profile;
import com.yedy.chat_app.exception.ProfileNotFoundException;
import com.yedy.chat_app.mapper.ProfileMapper;
import com.yedy.chat_app.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile findByUserId(String userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile findById(String id) {
        return profileRepository.findById(id).orElseThrow(()-> new ProfileNotFoundException("Profile not found"));
    }

    public List<ProfileDto> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAllByUserIdNotContains(UserContext.getId());
        List<ProfileDto> profileDtos = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDtos.add(ProfileMapper.INSTANCE.profileToProfileDto(profile));
        }
        return profileDtos;
    }

}
