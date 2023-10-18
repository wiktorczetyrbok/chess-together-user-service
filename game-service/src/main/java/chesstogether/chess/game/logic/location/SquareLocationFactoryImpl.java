package chesstogether.chess.game.logic.location;

import chesstogether.chess.game.Square;
import chesstogether.chess.game.physics.SquareLocation;
import org.springframework.stereotype.Component;


@Component("squareLocationFactoryImpl")
public class SquareLocationFactoryImpl implements SquareLocationFactory {
    @Override
    public SquareLocation buildSquareLocation(Square square) {
        SquareLocation squareLocation = new SquareLocation();
        squareLocation.setX(square.getX());
        squareLocation.setY(square.getY());
        return squareLocation;
    }
}
