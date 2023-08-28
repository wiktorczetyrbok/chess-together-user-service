package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Square;
import com.myApp.web.game.logic.SquareLocationFactory;
import com.myApp.web.game.logic.StatusMapFactory;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("statusMapFactoryImpl")
public class StatusMapFactoryImpl implements StatusMapFactory {
    private SquareLocationFactory squareLocationBuilder;

    @Override
    public Map<SquareLocation, SquareStatus> buildStatusMap(String activePlayer, List<Square> squareList) {
        Map<SquareLocation, SquareStatus> squareLocationMap = new HashMap<>();
        for (Square square : squareList) {
            SquareLocation squareLocation = squareLocationBuilder.buildSquareLocation(square);
            if (square.getPiece() == null) {
                squareLocationMap.put(squareLocation, SquareStatus.EMPTY);
            } else if (square.getPiece().getOwner().equals(activePlayer)) {
                squareLocationMap.put(squareLocation, SquareStatus.FRIENDLY);
            } else {
                squareLocationMap.put(squareLocation, SquareStatus.ENEMY);
            }
        }
        return squareLocationMap;
    }
}
