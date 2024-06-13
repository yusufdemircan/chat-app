package com.yedy.chat_app.controller;

import com.yedy.chat_app.dto.MatchDto;
import com.yedy.chat_app.dto.ProfileDto;
import com.yedy.chat_app.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getMatches")
    public List<MatchDto> getAllMatches() {
        return matchService.getMatchList();
    }


}
