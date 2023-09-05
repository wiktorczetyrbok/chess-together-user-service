package com.myApp.web.controller;

import com.myApp.web.dto.ChessComPlayerDto;
import com.myApp.web.service.LeaderboardScrapingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
@Controller
public class LeaderboardController {
    private final LeaderboardScrapingService scrapingService;

    public LeaderboardController(LeaderboardScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) throws IOException {
        List<ChessComPlayerDto> topPlayers = scrapingService.scrapeTopPlayers();
        model.addAttribute("topPlayers", topPlayers);
        return "leaderboard";
    }
}
