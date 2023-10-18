package com.myApp.web.controller;

import com.myApp.web.dto.ChessComPlayerDto;
import com.myApp.web.service.LeaderboardScrapingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LeaderboardController {
    private final LeaderboardScrapingService scrapingService;

    public LeaderboardController(LeaderboardScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ChessComPlayerDto>> getTopPlayers() {
        try {
            List<ChessComPlayerDto> topPlayers = scrapingService.scrapeTopPlayers();
            return ResponseEntity.ok(topPlayers);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
