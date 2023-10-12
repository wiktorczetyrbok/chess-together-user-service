package chesstogether.chess.game.logic.status;

import chesstogether.chess.game.Square;
import chesstogether.chess.game.physics.SquareLocation;
import chesstogether.chess.game.utils.Player;
import chesstogether.chess.game.utils.SquareStatus;

import java.util.List;
import java.util.Map;

public interface StatusMapFactory {
    Map<SquareLocation, SquareStatus> buildStatusMap(Player activePlayer, List<Square> squareList);
}
