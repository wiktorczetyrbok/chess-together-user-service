package chesstogether.chess.game.controller;

import chesstogether.chess.game.Board;
import chesstogether.chess.game.service.ChessGameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chess")
@RestController
public class ChessBoardController {
    private final ChessGameService chessGameService;

    public ChessBoardController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @RequestMapping("/game")
    public Board getGameState(
            @RequestParam(value = "x1", required = false) String x1,
            @RequestParam(value = "y1", required = false) String y1,
            @RequestParam(value = "x2", required = false) String x2,
            @RequestParam(value = "y2", required = false) String y2) {

        return chessGameService.getGameBoard(x1, y1, x2, y2);
    }
}
