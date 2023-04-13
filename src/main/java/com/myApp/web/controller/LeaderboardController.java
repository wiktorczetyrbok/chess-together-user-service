package com.myApp.web.controller;

import com.myApp.web.model.ChessComPlayer;
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
    @GetMapping("/leaderboard")
    public String leaderboard(Model model) throws IOException {
        List<ChessComPlayer> topPlayers = new ArrayList<>();
        Document document = Jsoup.connect("https://www.chess.com/players").timeout(6000).get();
        Elements players = document.select("div.post-preview-list-component");
        for (Element player : players.select("div.post-author-component")) {
            Element nameElement = player.selectFirst(".post-author-name");
            String name = nameElement.text();

            Element rankingElement = player.selectFirst(".master-players-world-stats");
            String ranking = rankingElement.text();

            Element imgElement = player.selectFirst(".post-author-thumbnail");
            String imgSrc = imgElement.attr("src");

            if( imgSrc.equals("/bundles/web/images/user-image.007dad08.svg")){
                imgSrc = imgElement.attr("data-src");
            }
            topPlayers.add(new ChessComPlayer(imgSrc, name, ranking));
        }

        model.addAttribute("topPlayers", topPlayers);
        return "leaderboard";
    }

}