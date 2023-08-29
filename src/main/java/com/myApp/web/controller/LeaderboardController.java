package com.myApp.web.controller;

import com.myApp.web.dto.ChessComPlayerDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LeaderboardController {
    //web scraping chess.com with Jsoup
    @GetMapping("/leaderboard")
    public String leaderboard(Model model) throws IOException {
        List<ChessComPlayerDto> topPlayers = new ArrayList<>();
        Document document = Jsoup.connect("https://www.chess.com/players")
                .timeout(6000)
                .get();

        Elements players = document.select("div.post-preview-list-component");

        for (Element player : players.select("div.post-author-component")) {
            String name = player.selectFirst(".post-author-name").text();
            String ranking = player.selectFirst(".master-players-world-stats").text();
            String imgSrc = player.selectFirst(".post-author-thumbnail")
                    .attr("src");

            if (imgSrc.equals("/bundles/web/images/user-image.007dad08.svg")) {
                imgSrc = player.selectFirst(".post-author-thumbnail")
                        .attr("data-src");
            }

            topPlayers.add(new ChessComPlayerDto(imgSrc, name, ranking));
        }

        model.addAttribute("topPlayers", topPlayers);

        return "leaderboard";
    }

}
