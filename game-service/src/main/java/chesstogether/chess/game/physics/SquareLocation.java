package chesstogether.chess.game.physics;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SquareLocation {

    private int x;
    private int y;

    public boolean equals(Object o1) {
        if (o1 instanceof SquareLocation squareLocation) {
            return this.x == squareLocation.x && this.y == squareLocation.y;
        }
        return false;
    }
}
