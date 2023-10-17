package chesstogether.chess.game.logic.moveCalc;

import chesstogether.chess.game.Move;
import chesstogether.chess.game.physics.SquareLocation;
import chesstogether.chess.game.physics.SquareLocationNavigator;
import chesstogether.chess.game.physics.Vector;
import chesstogether.chess.game.utils.SquareStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MultipleMoveCalc implements MoveCalc {
    public abstract List<Vector> getVectors();

    @Override
    public List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Vector vector : getVectors()) {
            SquareLocation currentLocation = pieceLocation;
            SquareStatus status;
            do {
                currentLocation = SquareLocationNavigator.applyVectorToSquareLocation(currentLocation, vector);
                status = locationStatusMap.get(currentLocation);
                if (status == SquareStatus.ENEMY || status == SquareStatus.EMPTY) {
                    Move move = new Move(
                            pieceLocation.getX(), pieceLocation.getY(),
                            currentLocation.getX(), currentLocation.getY()
                    );
                    possibleMoves.add(move);
                }
            } while (status == SquareStatus.EMPTY);
        }
        return possibleMoves;
    }
}
