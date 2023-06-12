package com.myApp.web.game.logic;

import com.myApp.web.game.Piece;

public interface MoveCalcFactory {
    MoveCalc buildMoveCalculator(Piece piece);
}
