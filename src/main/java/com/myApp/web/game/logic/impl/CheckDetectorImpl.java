package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Board;
import com.myApp.web.game.Move;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.BoardMovementGenerator;
import com.myApp.web.game.logic.CheckDetector;
import com.myApp.web.game.logic.RoyalPieceExtractor;
import com.myApp.web.game.utils.Player;

import java.util.List;
import java.util.Set;

public class CheckDetectorImpl implements CheckDetector {

    private final RoyalPieceExtractor royalPieceExtractor;
    private final BoardMovementGenerator boardMovementGenerator;

    public CheckDetectorImpl(RoyalPieceExtractor royalPieceExtractor, BoardMovementGenerator boardMovementGenerator) {
        this.royalPieceExtractor = royalPieceExtractor;
        this.boardMovementGenerator = boardMovementGenerator;
    }

    @Override
    public boolean detectCheck(Board board) {
        Player attackingPlayer = toggleActivePlayer(board.getActivePlayer());
        Set<Square> squares = royalPieceExtractor.extractRoyalPieces(board, board.getActivePlayer());
        List<Move> moves = boardMovementGenerator.generatePossibleMoves(attackingPlayer, board.getSquares());
        for (Move move : moves) {
            if (moveEndsInASquareWithARoyalPiece(move.getX2(), move.getY2(), squares)) {
                return true;
            }
        }
        return false;
    }

    private Player toggleActivePlayer(Player activePlayer) {
        return (activePlayer.equals(Player.BLACK)) ? Player.WHITE : Player.BLACK;
    }

    private boolean moveEndsInASquareWithARoyalPiece(int x, int y, Set<Square> squares) {
        for (Square square : squares) {
            if (square.getX() == x && square.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
