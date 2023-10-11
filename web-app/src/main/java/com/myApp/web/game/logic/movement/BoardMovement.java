package com.myApp.web.game.logic.movement;

import com.myApp.web.game.Board;
import com.myApp.web.game.Move;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;

public interface BoardMovement {
    Board makeMoveOnBoard(Board board, Move move);
    default Piece clonePiece(Piece piece) {
        if (piece == null) {
            return null;
        }

        return new Piece(piece.getOwner(), piece.getType(), piece.isRoyal(), piece.isHasMoved());
    }

    default Piece getPieceToBeMoved(Board board, Move move) {
        for (Square square : board.getSquares()) {
            if (move.getX1() == square.getX() && move.getY1() == square.getY()) {
                return square.getPiece();
            }
        }
        return null;
    }
}
