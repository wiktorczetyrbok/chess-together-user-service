package chesstogether.chess.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Move {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
}
