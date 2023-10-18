package chesstogether.chess.game.logic.moveCalc;

import chesstogether.chess.game.Move;
import chesstogether.chess.game.physics.SquareLocation;
import chesstogether.chess.game.physics.SquareLocationNavigator;
import chesstogether.chess.game.physics.Vector;
import chesstogether.chess.game.utils.SquareStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SingleMoveCalc implements MoveCalc {
    public abstract List<Vector> getVectors();

    @Override
    public List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Vector vector : getVectors()) {
            SquareLocation moveLocation = SquareLocationNavigator.applyVectorToSquareLocation(pieceLocation, vector);
            SquareStatus status = locationStatusMap.get(moveLocation);
            if (status == SquareStatus.ENEMY || status == SquareStatus.EMPTY) {
                Move move = new Move(
                        pieceLocation.getX(), pieceLocation.getY(),
                        moveLocation.getX(), moveLocation.getY()
                );
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }
}
