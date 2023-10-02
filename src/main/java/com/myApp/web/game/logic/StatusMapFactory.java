package com.myApp.web.game.logic;

import com.myApp.web.game.Square;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareStatus;
import com.myApp.web.game.utils.Player;

import java.util.List;
import java.util.Map;

public interface StatusMapFactory {
    Map<SquareLocation, SquareStatus> buildStatusMap(Player activePlayer, List<Square> squareList);
}
