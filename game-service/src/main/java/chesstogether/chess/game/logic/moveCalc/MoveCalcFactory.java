package chesstogether.chess.game.logic.moveCalc;

import chesstogether.chess.game.Piece;

public interface MoveCalcFactory {
    MoveCalc buildMoveCalculator(Piece piece);
}
