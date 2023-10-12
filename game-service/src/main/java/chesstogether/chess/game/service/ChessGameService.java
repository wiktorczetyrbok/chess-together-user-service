package chesstogether.chess.game.service;

import chesstogether.chess.game.Board;

public interface ChessGameService {
    Board getGameBoard(String x1, String y1, String x2, String y2);
}
