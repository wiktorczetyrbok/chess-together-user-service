package com.myApp.web.game.logic.impl.moveCalc.pieces;

import com.myApp.web.game.logic.impl.moveCalc.SingleMoveCalc;
import com.myApp.web.game.physics.Vector;

import java.util.ArrayList;
import java.util.List;

public class KnightMoveCalc extends SingleMoveCalc {
    @Override
    public List<Vector> getVectors() {
        List<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(1, 2));
        vectors.add(new Vector(-1, 2));
        vectors.add(new Vector(1, -2));
        vectors.add(new Vector(-1, -2));
        vectors.add(new Vector(2, 1));
        vectors.add(new Vector(-2, 1));
        vectors.add(new Vector(2, -1));
        vectors.add(new Vector(-2, -1));
        return vectors;
    }
}
