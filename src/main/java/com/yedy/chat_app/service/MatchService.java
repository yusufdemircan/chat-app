package com.yedy.chat_app.service;

import com.yedy.chat_app.entity.Match;
import com.yedy.chat_app.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void save(Match match) {
        matchRepository.save(match);
    }
}
