package com.myApp.web.game.logic.check;

import com.myApp.web.game.Board;

public interface CheckDetector {
    boolean detectCheck(Board board);
}
