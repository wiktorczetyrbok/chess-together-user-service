package com.myApp.web.game.logic;

import com.myApp.web.game.Square;
import com.myApp.web.game.physics.SquareLocation;

public interface SquareLocationFactory {
    SquareLocation buildSquareLocation(Square square);
}
