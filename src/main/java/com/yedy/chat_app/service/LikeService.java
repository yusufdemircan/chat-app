package com.yedy.chat_app.service;
import com.yedy.chat_app.entity.Like;
import com.yedy.chat_app.entity.Match;
import com.yedy.chat_app.entity.Profile;
import com.yedy.chat_app.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    public List<Like> getUserLikes(UUID userId) {
        return likeRepository.findByLikerUserId(userId);
    }

    public List<Like> getMutualLikes() {
        return likeRepository.findByMutual(true);
    }

    public void likeProfile(UUID userId, UUID profileId) {
        Like existingLike = likeRepository.findByLikerUserIdAndLikedProfileId(userId, profileId);
        if (existingLike != null) {
            return;
        }

        Like like = new Like();
        like.setLikerUserId(userId);
        like.setLikedProfileId(profileId);
        like.setLikedAt(new Date());
        likeRepository.save(like);


        //TODO RABBİTMQ KUYRUĞUNA ALINACAK VE EŞLEŞME OLUNCA İKİ KİŞİYEDE BİLDİRİM ATILACAK
        Profile profile = profileService.findById(like.getLikedProfileId());
        Like likeMatch = likeRepository.findByLikerUserIdAndLikedProfileId(profile.getUserId(),like.getLikerUserId());

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
        }
    }
}