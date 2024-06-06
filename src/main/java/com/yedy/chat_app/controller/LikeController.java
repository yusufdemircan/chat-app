package com.yedy.chat_app.controller;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Like>> getUserLikes(@PathVariable UUID userId) {
        List<Like> userLikes = likeService.getUserLikes(userId);
        return new ResponseEntity<>(userLikes, HttpStatus.OK);
    }

    @GetMapping("/mutual")
    public ResponseEntity<List<Like>> getMutualLikes() {
        List<Like> mutualLikes = likeService.getMutualLikes();
        return new ResponseEntity<>(mutualLikes, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> likeProfile(@RequestParam UUID userId, @RequestParam UUID profileId) {
        likeService.likeProfile(userId, profileId);
        return new ResponseEntity<>("Profile liked successfully.", HttpStatus.OK);
    }
}