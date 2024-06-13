package com.yedy.chat_app.service;

import com.yedy.chat_app.entity.Match;
import com.yedy.chat_app.exception.YedyException;
import com.yedy.chat_app.repository.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void save(Match match) {
        matchRepository.save(match);
    }
    @Transactional
    public void deleteMatch(String matchId){
        Match match = matchRepository.findById(matchId).orElseThrow(()->new YedyException("Match not found"));
        matchRepository.deleteByUserId1AndUserId2(match.getUserId1(), match.getUserId2());
        matchRepository.deleteByUserId1AndUserId2(match.getUserId2(), match.getUserId1());
    }
}
