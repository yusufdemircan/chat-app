package com.yedy.chat_app.service;

import com.yedy.chat_app.consts.UserContext;
import com.yedy.chat_app.dto.MatchDto;
import com.yedy.chat_app.dto.ProfileDto;
import com.yedy.chat_app.entity.Match;
import com.yedy.chat_app.entity.Profile;
import com.yedy.chat_app.exception.YedyException;
import com.yedy.chat_app.mapper.ProfileMapper;
import com.yedy.chat_app.repository.MatchRepository;
import com.yedy.chat_app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final ProfileService profileService;

    public MatchService(MatchRepository matchRepository, ProfileService profileService) {
        this.matchRepository = matchRepository;
        this.profileService = profileService;
    }

    public void save(Match match) {
        matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(String matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new YedyException("Match not found"));
        matchRepository.deleteByUserId1AndUserId2(match.getUserId1(), match.getUserId2());
        matchRepository.deleteByUserId1AndUserId2(match.getUserId2(), match.getUserId1());
    }

    public List<MatchDto> getMatchList() {
        List<Match> matchList = matchRepository.findByUserId1(UserContext.getId());
        List<MatchDto> profileDtoList = new ArrayList<>();
        for (Match match : matchList) {
            MatchDto matchDto = new MatchDto();
            matchDto.setMatchId(match.getId());
            matchDto.setProfileDto(ProfileMapper.INSTANCE.profileToProfileDto(profileService.findByUserId(match.getUserId2())));

            profileDtoList.add(matchDto);

        }
        return profileDtoList;
    }
}
