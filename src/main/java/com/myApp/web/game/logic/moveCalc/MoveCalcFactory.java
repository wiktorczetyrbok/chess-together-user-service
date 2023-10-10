package com.myApp.web.game.logic.moveCalc;

import com.myApp.web.game.Piece;

public interface MoveCalcFactory {
    MoveCalc buildMoveCalculator(Piece piece);
}
