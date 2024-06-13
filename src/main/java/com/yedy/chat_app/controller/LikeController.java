package com.yedy.chat_app.controller;
import com.yedy.chat_app.consts.UserContext;
import com.yedy.chat_app.dto.LikeDto;
import com.yedy.chat_app.entity.Like;
import com.yedy.chat_app.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/getLikes")
    public ResponseEntity<List<LikeDto>> getUserLikes() {
        List<LikeDto> userLikes = likeService.getUserLikes(UserContext.getId());
        return new ResponseEntity<>(userLikes, HttpStatus.OK);
    }

    @GetMapping("/mutual")
    public ResponseEntity<List<Like>> getMutualLikes() {
        List<Like> mutualLikes = likeService.getMutualLikes();
        return new ResponseEntity<>(mutualLikes, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> likeProfile(@RequestParam String profileId) {
        return ResponseEntity.ok(likeService.likeProfile(UserContext.getId(), profileId));
    }

    @DeleteMapping("/delete/{likeId}")
    public void deleteLike(@PathVariable String likeId) {
        likeService.deleteLike(likeId);
    }
}