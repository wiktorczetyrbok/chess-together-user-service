package chesstogether.chess.game.logic.status;

import chesstogether.chess.game.Square;
import chesstogether.chess.game.logic.location.SquareLocationFactory;
import chesstogether.chess.game.physics.SquareLocation;
import chesstogether.chess.game.utils.Player;
import chesstogether.chess.game.utils.SquareStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("statusMapFactoryImpl")
public class StatusMapFactoryImpl implements StatusMapFactory {
    private final SquareLocationFactory squareLocationBuilder;

    public StatusMapFactoryImpl(SquareLocationFactory squareLocationBuilder) {
        this.squareLocationBuilder = squareLocationBuilder;
    }

    @Override
    public Map<SquareLocation, SquareStatus> buildStatusMap(Player activePlayer, List<Square> squareList) {
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
