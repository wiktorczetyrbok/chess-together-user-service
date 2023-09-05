package com.myApp.web.service;

import com.myApp.web.dto.ChessComPlayerDto;

import java.io.IOException;
import java.util.List;

public interface LeaderboardScrapingService {
    List<ChessComPlayerDto> scrapeTopPlayers() throws IOException;
}
