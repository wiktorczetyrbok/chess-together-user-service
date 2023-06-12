package com.myApp.web.game.logic;

import com.myApp.web.game.Move;
import com.myApp.web.game.Square;

import java.util.List;

public interface BoardMovementGenerator {
    List<Move> generatePossibleMoves(String activePlayer, List<Square> squares);

}
