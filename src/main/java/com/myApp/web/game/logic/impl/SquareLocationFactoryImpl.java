package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Square;
import com.myApp.web.game.logic.SquareLocationFactory;
import com.myApp.web.game.physics.SquareLocation;
import org.springframework.stereotype.Component;


@Component("squareLocationFactoryImpl")
public class SquareLocationFactoryImpl implements SquareLocationFactory {
    @Override
    public SquareLocation buildSquareLocation(Square square) {
        SquareLocation squareLocation = new SquareLocation();
        squareLocation.setX(square.getX());
        squareLocation.setY(square.getY());
        return squareLocation;
    }
}
