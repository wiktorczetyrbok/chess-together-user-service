package com.myApp.web.controller;

import com.myApp.web.model.ChessComPlayer;
import io.github.sornerol.chess.pubapi.client.LeaderboardsClient;
import io.github.sornerol.chess.pubapi.domain.leaderboards.LeaderboardEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LeaderboardController {
    @GetMapping("/leaderboard")
    public String leaderboard(Model model){
        List<ChessComPlayer> topPlayers = new ArrayList<>();

        LeaderboardEntry entry = new LeaderboardEntry();
        LeaderboardsClient client = new LeaderboardsClient();
        //client.getLeaderboards().getDaily960Leaderboard();
        try {
            for (int i = 1; i < 10; i++) {
                ChessComPlayer player = new ChessComPlayer(i, entry.getUsername(), entry.getScore());
                topPlayers.add(player);
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        model.addAttribute("topPlayers", topPlayers);
        return "leaderboard";
    }

}
