package chesstogether.chess.game.logic.check;

import chesstogether.chess.game.Board;

public interface CheckDetector {
    boolean detectCheck(Board board);
}
