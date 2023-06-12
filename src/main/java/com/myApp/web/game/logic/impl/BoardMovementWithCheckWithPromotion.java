package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Board;
import com.myApp.web.game.Move;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.SquareLocationFactory;
import com.myApp.web.game.logic.StatusMapFactory;
import com.myApp.web.game.logic.impl.moveCalc.pieces.PawnMoveCalc;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("boardMovementWithCheckWithPromotion")
public class BoardMovementWithCheckWithPromotion extends BoardMovementWithCheckNoPromotion{
    @Autowired
    private StatusMapFactory statusMapFactory;

    @Autowired
    private SquareLocationFactory squareLocationFactory;

    @Override
    public Board makeMoveOnBoard(Board board, Move move) {

        Board newBoard = super.makeMoveOnBoard(board, move);

        Map<SquareLocation, SquareStatus> locationStatusMap
                = statusMapFactory.buildStatusMap(board.getActivePlayer(), board.getSquares());
        for (Square square : newBoard.getSquares()) {
            Piece piece = square.getPiece();
            if (piece!=null && piece.getType().equals("Pawn")) {
                SquareLocation pieceLocation = squareLocationFactory.buildSquareLocation(square);

                PawnMoveCalc pawnMoveCalc= new PawnMoveCalc(piece.getOwner(), piece.isHasMoved());
                if (pawnMoveCalc.pawnCanBePromoted(pieceLocation, locationStatusMap)) {
                    square.setPiece(new Piece(piece.getOwner(), "Queen", false));
                }
            }
        }
        return newBoard;
    }
}
