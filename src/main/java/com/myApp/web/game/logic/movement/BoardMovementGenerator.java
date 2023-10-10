package com.myApp.web.game.logic.movement;

import com.myApp.web.game.Move;
import com.myApp.web.game.Square;
import com.myApp.web.game.utils.Player;

import java.util.List;

public interface BoardMovementGenerator {
    List<Move> generatePossibleMoves(Player activePlayer, List<Square> squares);

}
