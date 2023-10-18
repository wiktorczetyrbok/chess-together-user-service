package chesstogether.chess.game.logic.pieceExtractor;

import chesstogether.chess.game.Board;
import chesstogether.chess.game.Piece;
import chesstogether.chess.game.Square;
import chesstogether.chess.game.utils.Player;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("royalPieceExtractor")
public class RoyalPieceExtractorImpl implements RoyalPieceExtractor {
    @Override
    public Set<Square> extractRoyalPieces(Board board, Player targetPlayer) {
        Set<Square> squaresWithRoyalPieces = new HashSet<>();
        for (Square square : board.getSquares()) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getOwner().equals(targetPlayer) && piece.isRoyal()) {
                squaresWithRoyalPieces.add(square);
            }
        }
        return squaresWithRoyalPieces;
    }
}
