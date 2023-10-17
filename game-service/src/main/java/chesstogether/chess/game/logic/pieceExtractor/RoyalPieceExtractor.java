package chesstogether.chess.game.logic.pieceExtractor;

import chesstogether.chess.game.Board;
import chesstogether.chess.game.Square;
import chesstogether.chess.game.utils.Player;

import java.util.Set;

public interface RoyalPieceExtractor {
    Set<Square> extractRoyalPieces(Board board, Player targetPlayer);
}
