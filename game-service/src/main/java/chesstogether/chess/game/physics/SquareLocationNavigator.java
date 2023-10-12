package chesstogether.chess.game.physics;

public class SquareLocationNavigator {
    public static SquareLocation applyVectorToSquareLocation(SquareLocation squareLocation, Vector v) {
        SquareLocation squareLocationChanged = new SquareLocation();
        squareLocationChanged.setX(squareLocation.getX() + v.getX());
        squareLocationChanged.setY(squareLocation.getY() + v.getY());
        return squareLocationChanged;
    }
}
