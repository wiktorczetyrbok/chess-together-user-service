package com.myApp.web.game.logic.pieceExtractor;

import com.myApp.web.game.Square;
import com.myApp.web.game.utils.Player;

import java.util.List;

public interface ActivePieceExtractor {
    List<Square> extractSquaresWithActivePieces(Player activePlayer, List<Square> squares);
}
