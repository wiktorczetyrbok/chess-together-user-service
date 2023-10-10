package com.myApp.web.game.logic.moveCalc;

import com.myApp.web.game.Move;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareLocationNavigator;
import com.myApp.web.game.physics.Vector;
import com.myApp.web.game.utils.SquareStatus;

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
