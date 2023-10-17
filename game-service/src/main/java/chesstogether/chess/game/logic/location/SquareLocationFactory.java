package chesstogether.chess.game.logic.location;

import chesstogether.chess.game.Square;
import chesstogether.chess.game.physics.SquareLocation;

public interface SquareLocationFactory {
    SquareLocation buildSquareLocation(Square square);
}
