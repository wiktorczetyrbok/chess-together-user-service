package chesstogether.chess.game.logic.movement;

import chesstogether.chess.game.Move;
import chesstogether.chess.game.Square;
import chesstogether.chess.game.utils.Player;

import java.util.List;

public interface BoardMovementGenerator {
    List<Move> generatePossibleMoves(Player activePlayer, List<Square> squares);

}
