package chesstogether.chess.game.logic.check;

import chesstogether.chess.game.Board;
import chesstogether.chess.game.Move;
import chesstogether.chess.game.Square;
import chesstogether.chess.game.logic.movement.BoardMovementGenerator;
import chesstogether.chess.game.logic.pieceExtractor.RoyalPieceExtractor;
import chesstogether.chess.game.utils.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static chesstogether.chess.game.utils.PlayerUtils.toggleActivePlayer;

@Component
public class CheckDetectorImpl implements CheckDetector {

    private final RoyalPieceExtractor royalPieceExtractor;
    private final BoardMovementGenerator boardMovementGenerator;

    public CheckDetectorImpl(RoyalPieceExtractor royalPieceExtractor, BoardMovementGenerator boardMovementGenerator) {
        this.royalPieceExtractor = royalPieceExtractor;
        this.boardMovementGenerator = boardMovementGenerator;
    }

    @Override
    public boolean detectCheck(Board board) {
        Player attackingPlayer = toggleActivePlayer(board.getActivePlayer());
        Set<Square> squares = royalPieceExtractor.extractRoyalPieces(board, board.getActivePlayer());
        List<Move> moves = boardMovementGenerator.generatePossibleMoves(attackingPlayer, board.getSquares());
        for (Move move : moves) {
            if (moveEndsInASquareWithARoyalPiece(move.getX2(), move.getY2(), squares)) {
                return true;
            }
        }
        return false;
    }


    private boolean moveEndsInASquareWithARoyalPiece(int x, int y, Set<Square> squares) {
        for (Square square : squares) {
            if (square.getX() == x && square.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
