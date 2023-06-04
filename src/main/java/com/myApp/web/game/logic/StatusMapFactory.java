package com.myApp.web.game.logic;

import com.myApp.web.game.Square;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareStatus;

import java.util.List;
import java.util.Map;

public interface StatusMapFactory {
    Map<SquareLocation, SquareStatus> buildStatusMap(String activePlayer, List<Square> squareList);
}
