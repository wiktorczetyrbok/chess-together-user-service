package com.myApp.web.game.logic;

import com.myApp.web.game.Board;
import com.myApp.web.game.Move;

public interface BoardMovement {
    Board makeMoveOnBoard(Board board, Move move);
}
