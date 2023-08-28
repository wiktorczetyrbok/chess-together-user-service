package com.myApp.web.game.logic.impl.moveCalc.pieces;

import com.myApp.web.game.logic.impl.moveCalc.MultipleMoveCalc;
import com.myApp.web.game.physics.Vector;

import java.util.ArrayList;
import java.util.List;

public class BishopMoveCalc extends MultipleMoveCalc {
    @Override
    public List<Vector> getVectors() {
        List<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(1, 1));
        vectors.add(new Vector(-1, 1));
        vectors.add(new Vector(1, -1));
        vectors.add(new Vector(-1, -1));
        return vectors;
    }
}
