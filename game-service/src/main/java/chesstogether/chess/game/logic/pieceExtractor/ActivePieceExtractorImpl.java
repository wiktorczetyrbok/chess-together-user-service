package chesstogether.chess.game.logic.pieceExtractor;

import chesstogether.chess.game.Square;
import chesstogether.chess.game.utils.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("activePieceExtractorImpl")
public class ActivePieceExtractorImpl implements ActivePieceExtractor {
    @Override
    public List<Square> extractSquaresWithActivePieces(Player activePlayer, List<Square> squares) {
        List<Square> activeSquares = new ArrayList<>();
        for (Square square : squares) {
            if (square.getPiece() != null && square.getPiece().getOwner().equals(activePlayer)) {
                activeSquares.add(square);
            }
        }
        return activeSquares;
    }
}
