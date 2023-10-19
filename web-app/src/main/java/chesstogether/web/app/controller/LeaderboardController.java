package chesstogether.web.app.controller;

import chesstogether.web.app.dto.ChessComPlayerDto;
import chesstogether.web.app.service.LeaderboardScrapingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/leaderboard")
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
