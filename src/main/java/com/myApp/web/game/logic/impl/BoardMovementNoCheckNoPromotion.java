package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Board;
import com.myApp.web.game.Move;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.BoardMovement;
import com.myApp.web.game.logic.BoardMovementGenerator;
import com.myApp.web.game.utils.Player;
import com.myApp.web.game.utils.Type;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.myApp.web.game.utils.PlayerUtils.toggleActivePlayer;

@Getter
public class BoardMovementNoCheckNoPromotion implements BoardMovement {
    public BoardMovementNoCheckNoPromotion(BoardMovementGenerator boardMoveGenerator) {
        this.boardMoveGenerator = boardMoveGenerator;
    }

    private final BoardMovementGenerator boardMoveGenerator;

    @Override
    public Board makeMoveOnBoard(Board board, Move move) {
        Piece pieceToBeMoved = getPieceToBeMoved(board, move);
        List<Square> squares = new ArrayList<>();
        for (Square square : board.getSquares()) {
            Square squareClone = new Square();
            squareClone.setX(square.getX());
            squareClone.setY(square.getY());
            if (move.getX1() == square.getX() && move.getY1() == square.getY()) {
                squareClone.setPiece(null);
            } else if (move.getX2() == square.getX() && move.getY2() == square.getY()) {
                squareClone.setPiece(clonePiece(pieceToBeMoved));
                if (squareClone.getPiece() != null) {
                    squareClone.getPiece().setHasMoved(true);
                }
            } else {
                squareClone.setPiece(clonePiece(square.getPiece()));
            }
            squares.add(squareClone);
        }
        Board newBoard = new Board();
        newBoard.setActivePlayer(toggleActivePlayer(board.getActivePlayer()));
        newBoard.setSquares(squares);
        List<Move> potentialMoves = boardMoveGenerator.generatePossibleMoves(newBoard.getActivePlayer(), newBoard.getSquares());
        newBoard.setMoves(potentialMoves);
        List<Type> promotionPieces = new ArrayList<>();
        promotionPieces.addAll(board.getPromotionPieces());
        newBoard.setPromotionPieces(promotionPieces);
        return newBoard;
    }

    private Piece getPieceToBeMoved(Board board, Move move) {
        for (Square square : board.getSquares()) {
            if (move.getX1() == square.getX() && move.getY1() == square.getY()) {
                return square.getPiece();
            }
        }
        return null;
    }

    private Piece clonePiece(Piece piece) {
        if (piece == null) {
            return null;
        }
        Piece newPiece = new Piece(piece.getOwner(), piece.getType(), piece.isRoyal(), piece.isHasMoved());

        return newPiece;
    }
}
