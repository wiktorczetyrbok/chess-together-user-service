package com.myApp.web.game.logic.movement.impl;

import com.myApp.web.game.Board;
import com.myApp.web.game.Move;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.check.CheckDetector;
import com.myApp.web.game.logic.location.SquareLocationFactory;
import com.myApp.web.game.logic.moveCalc.pieces.PawnMoveCalc;
import com.myApp.web.game.logic.movement.BoardMovementGenerator;
import com.myApp.web.game.logic.status.StatusMapFactory;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.utils.SquareStatus;
import com.myApp.web.game.utils.Type;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("boardMovementWithCheckWithPromotion")
public class BoardMovementWithCheckWithPromotion extends BoardMovementWithCheckNoPromotion {
    private final StatusMapFactory statusMapFactory;
    private final SquareLocationFactory squareLocationFactory;

    public BoardMovementWithCheckWithPromotion(BoardMovementGenerator boardMovementGenerator,
                                               CheckDetector checkDetector,
                                               StatusMapFactory statusMapFactory,
                                               SquareLocationFactory squareLocationFactory) {
        super(boardMovementGenerator, checkDetector);
        this.statusMapFactory = statusMapFactory;
        this.squareLocationFactory = squareLocationFactory;
    }

    @Override
    public Board makeMoveOnBoard(Board board, Move move) {

        Board newBoard = super.makeMoveOnBoard(board, move);

        Map<SquareLocation, SquareStatus> locationStatusMap
                = statusMapFactory.buildStatusMap(board.getActivePlayer(), board.getSquares());
        for (Square square : newBoard.getSquares()) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getType().equals(Type.PAWN)) {
                SquareLocation pieceLocation = squareLocationFactory.buildSquareLocation(square);

                PawnMoveCalc pawnMoveCalc = new PawnMoveCalc(piece.getOwner(), piece.isHasMoved());
                if (pawnMoveCalc.pawnCanBePromoted(pieceLocation, locationStatusMap)) {
                    square.setPiece(new Piece(piece.getOwner(), Type.QUEEN, false, piece.isHasMoved()));
                }
            }
        }
        return newBoard;
    }
}
