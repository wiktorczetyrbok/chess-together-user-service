package chesstogether.chess.game.logic.moveCalc.pieces;

import chesstogether.chess.game.logic.moveCalc.MultipleMoveCalc;
import chesstogether.chess.game.physics.Vector;

import java.util.ArrayList;
import java.util.List;

public class RookMoveCalc extends MultipleMoveCalc {
    @Override
    public List<Vector> getVectors() {
        List<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(1, 0));
        vectors.add(new Vector(0, 1));
        vectors.add(new Vector(-1, 0));
        vectors.add(new Vector(0, -1));
        return vectors;
    }
}
