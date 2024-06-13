package com.yedy.chat_app.service;

import com.yedy.chat_app.consts.UserContext;
import com.yedy.chat_app.dto.LikeDto;
import com.yedy.chat_app.dto.ProfileDto;
import com.yedy.chat_app.entity.Like;
import com.yedy.chat_app.entity.Match;
import com.yedy.chat_app.entity.Profile;
import com.yedy.chat_app.mapper.ProfileMapper;
import com.yedy.chat_app.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final ProfileService profileService;
    private final MatchService matchService;

    public LikeService(LikeRepository likeRepository, ProfileService profileService, MatchService matchService) {
        this.likeRepository = likeRepository;
        this.profileService = profileService;
        this.matchService = matchService;

    }

    public List<LikeDto> getUserLikes(String userId) {
        List<Like> likes = likeRepository.findByLikerUserId(userId);
        List<LikeDto> likeDtos = new ArrayList<>();
        likes.forEach(l -> {
            Profile p = profileService.findById(l.getLikedProfileId());
            ProfileDto pDto = ProfileMapper.INSTANCE.profileToProfileDto(p);
            LikeDto likeDto = new LikeDto();
            likeDto.setLikeProfile(pDto);
            likeDto.setId(l.getId());
            likeDto.setMutual(l.isMutual());
            likeDto.setLikedAt(l.getLikedAt());
            likeDtos.add(likeDto);

        });

        return likeDtos;

    }

    public List<Like> getMutualLikes() {
        return likeRepository.findByMutual(true);
    }

    @Transactional
    public String likeProfile(String userId, String profileId) {
        Like existingLike = likeRepository.findByLikerUserIdAndLikedProfileId(userId, profileId);
        if (existingLike != null) {
            return "Profile already liked";
        }

        Like like = new Like();
        like.setLikerUserId(userId);
        like.setLikedProfileId(profileId);
        like.setLikedAt(new Date());
        likeRepository.save(like);


        //TODO RABBİTMQ KUYRUĞUNA ALINACAK VE EŞLEŞME OLUNCA İKİ KİŞİYEDE BİLDİRİM ATILACAK
        Profile profile = profileService.findById(like.getLikedProfileId());
        Profile myProfile = profileService.findByUserId(UserContext.getId());

        Like likeMatch = likeRepository.findByLikerUserIdAndLikedProfileId(profile.getUserId(), myProfile.getId());
        if (likeMatch != null) {
            Match match = new Match();
            match.setMatchedAt(new Date());
            match.setUserId1(like.getLikerUserId());
            match.setUserId2(profile.getUserId());
            matchService.save(match);

            Match match2 = new Match();
            match2.setMatchedAt(new Date());
            match2.setUserId1(profile.getUserId());
            match2.setUserId2(like.getLikerUserId());
            matchService.save(match2);

            //TODO EŞLEŞMEDEN SONRA HER İKİ TARAF İÇİNDE BEĞENİLERİ SİL
            likeRepository.deleteByLikerUserIdAndLikedProfileId(UserContext.getId(), like.getLikedProfileId());
            likeRepository.deleteByLikerUserIdAndLikedProfileId(profile.getUserId(), myProfile.getId());
        }
        return "Profile liked";
    }

    public void deleteLike(String likeId) {
        likeRepository.deleteById(likeId);
    }
}