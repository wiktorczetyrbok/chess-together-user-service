package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Board;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.RoyalPieceExtractor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("royalPieceExtractor")
public class RoyalPieceExtractorImpl implements RoyalPieceExtractor {
    @Override
    public Set<Square> extractRoyalPieces(Board board, String targetPlayer) {
        Set<Square> squaresWithRoyalPieces = new HashSet<>();
        for (Square square : board.getSquares()) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getOwner().equals(targetPlayer) && piece.isRoyal()) {
                squaresWithRoyalPieces.add(square);
            }
        }
        return squaresWithRoyalPieces;
    }
}
