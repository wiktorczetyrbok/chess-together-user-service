package com.myApp.web.game.logic;

import com.myApp.web.game.Board;
import com.myApp.web.game.Square;

import java.util.Set;

public interface RoyalPieceExtractor {
    Set<Square> extractRoyalPieces(Board board, String targetPlayer);
}
