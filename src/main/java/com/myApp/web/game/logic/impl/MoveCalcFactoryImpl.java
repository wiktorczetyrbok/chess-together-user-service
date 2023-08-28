package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Piece;
import com.myApp.web.game.logic.MoveCalc;
import com.myApp.web.game.logic.MoveCalcFactory;
import com.myApp.web.game.logic.impl.moveCalc.pieces.*;
import org.springframework.stereotype.Component;

@Component("moveCalculatorFactoryImpl")
public class MoveCalcFactoryImpl implements MoveCalcFactory {

    @Override
    public MoveCalc buildMoveCalculator(Piece piece) {
        if (piece == null) {
            throw new RuntimeException("A null piece was passed to MoveCalculatorFactoryImpl.buildMoveCalculator(piece)");
        }
        String type = piece.getType();
        if (type.equals("Pawn")) {
            //Pawns need to know the owner and if it is a virgin pawn
            return new PawnMoveCalc(piece.getOwner(), piece.isHasMoved());
            //Other pieces move without regard to owner or previous move status
        } else if (type.equals("Rook")) {
            return new RookMoveCalc();
        } else if (type.equals("Knight")) {
            return new KnightMoveCalc();
        } else if (type.equals("Bishop")) {
            return new BishopMoveCalc();
        } else if (type.equals("King")) {
            return new KingMoveCalc();
        } else if (type.equals("Queen")) {
            return new QueenMoveCalc();
        } else {
            throw new RuntimeException("The following piece type was not found: " + type);
        }
    }
}
