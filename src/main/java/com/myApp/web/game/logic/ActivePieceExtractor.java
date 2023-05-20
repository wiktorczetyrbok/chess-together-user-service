package com.myApp.web.game.logic;

import com.myApp.web.game.Square;

import java.util.List;

public interface ActivePieceExtractor {
    List<Square> extractSquaresWithActivePieces(String activePlayer, List<Square> squares);
}
