package com.myApp.web.service.impl;


import com.myApp.web.dto.ChessComPlayerDto;
import com.myApp.web.service.LeaderboardScrapingService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardScrapingServiceImpl implements LeaderboardScrapingService {
    private static final int TIMEOUT_DURATION_MS = 6000;
    private static final String CHESS_COM_URL = "https://www.chess.com/players";
    private static final String DEFAULT_IMAGE_SRC = "/bundles/web/images/user-image.007dad08.svg";

    public List<ChessComPlayerDto> scrapeTopPlayers() throws IOException {
        Document document = Jsoup.connect(CHESS_COM_URL)
                .timeout(TIMEOUT_DURATION_MS)
                .get();

        Elements playerElements = document.select("div.post-author-component");

        return playerElements.stream()
                .map(this::mapToChessComPlayerDto)
                .collect(Collectors.toList());
    }

    private ChessComPlayerDto mapToChessComPlayerDto(Element player) {
        String name = player.selectFirst(".post-author-name").text();
        String ranking = player.selectFirst(".master-players-world-stats").text();
        String imgSrc = player.selectFirst(".post-author-thumbnail")
                .attr("src");

        if (imgSrc.equals(DEFAULT_IMAGE_SRC)) {
            imgSrc = player.selectFirst(".post-author-thumbnail")
                    .attr("data-src");
        }

        return new ChessComPlayerDto(imgSrc, name, ranking);
    }

}

