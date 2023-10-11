package com.myApp.web.game.logic.status;

import com.myApp.web.game.Square;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.utils.Player;
import com.myApp.web.game.utils.SquareStatus;

import java.util.List;
import java.util.Map;

public interface StatusMapFactory {
    Map<SquareLocation, SquareStatus> buildStatusMap(Player activePlayer, List<Square> squareList);
}
