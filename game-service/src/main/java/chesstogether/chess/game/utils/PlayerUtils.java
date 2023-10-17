package chesstogether.chess.game.utils;

public class PlayerUtils {
    public static Player toggleActivePlayer(Player activePlayer) {
        return (activePlayer.equals(Player.BLACK)) ? Player.WHITE : Player.BLACK;
    }
}
