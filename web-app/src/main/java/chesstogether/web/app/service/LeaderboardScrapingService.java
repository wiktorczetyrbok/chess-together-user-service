package chesstogether.web.app.service;

import chesstogether.web.app.dto.ChessComPlayerDto;

import java.io.IOException;
import java.util.List;

public interface LeaderboardScrapingService {
    List<ChessComPlayerDto> scrapeTopPlayers() throws IOException;
}
