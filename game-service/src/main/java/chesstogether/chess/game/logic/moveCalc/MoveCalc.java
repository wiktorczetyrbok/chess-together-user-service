package chesstogether.chess.game.logic.moveCalc;

import chesstogether.chess.game.Move;
import chesstogether.chess.game.physics.SquareLocation;
import chesstogether.chess.game.utils.SquareStatus;

import java.util.List;
import java.util.Map;

public interface MoveCalc {
    List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap);
}
