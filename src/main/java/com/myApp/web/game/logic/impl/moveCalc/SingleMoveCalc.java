package com.myApp.web.game.logic.impl.moveCalc;

import com.myApp.web.game.Move;
import com.myApp.web.game.logic.MoveCalc;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareLocationNavigator;
import com.myApp.web.game.physics.SquareStatus;
import com.myApp.web.game.physics.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SingleMoveCalc implements MoveCalc {
    public abstract List<Vector> getVectors();
    private SquareLocationNavigator squareLocationNavigator = new SquareLocationNavigator();

    @Override
    public List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Vector vector : getVectors()) {
            SquareLocation moveLocation = squareLocationNavigator.applyVectorToSquareLocation(pieceLocation, vector);
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
