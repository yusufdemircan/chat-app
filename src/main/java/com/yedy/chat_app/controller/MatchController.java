package com.yedy.chat_app.controller;

import com.yedy.chat_app.service.MatchService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    @DeleteMapping("/delete/{matchId}")
    public void deleteMatch(@PathVariable String matchId) {
        matchService.deleteMatch(matchId);
    }
}
