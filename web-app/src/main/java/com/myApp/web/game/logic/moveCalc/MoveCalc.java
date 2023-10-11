package com.myApp.web.game.logic.moveCalc;

import com.myApp.web.game.Move;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.utils.SquareStatus;

import java.util.List;
import java.util.Map;

public interface MoveCalc {
    List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap);
}
