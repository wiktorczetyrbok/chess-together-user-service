package chesstogether.chess.game.logic.pieceExtractor;

import chesstogether.chess.game.Square;
import chesstogether.chess.game.utils.Player;

import java.util.List;

public interface ActivePieceExtractor {
    List<Square> extractSquaresWithActivePieces(Player activePlayer, List<Square> squares);
}
