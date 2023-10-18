package chesstogether.chess.game;

import chesstogether.chess.game.utils.BoardStatus;
import chesstogether.chess.game.utils.Player;
import chesstogether.chess.game.utils.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {
    private Player activePlayer;
    private BoardStatus status;
    private List<Square> squares;
    private List<Move> moves;
    private List<Type> promotionPieces;
}

