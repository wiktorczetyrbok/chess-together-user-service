package chesstogether.chess.game;

import chesstogether.chess.game.utils.Player;
import chesstogether.chess.game.utils.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Piece {
    private Player owner;
    private Type type;
    private boolean royal;
    private boolean hasMoved;
}