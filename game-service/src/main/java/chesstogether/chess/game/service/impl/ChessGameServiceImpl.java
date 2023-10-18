package chesstogether.chess.game.service.impl;

import chesstogether.chess.game.Board;
import chesstogether.chess.game.DefaultBoardGenerator;
import chesstogether.chess.game.Move;
import chesstogether.chess.game.logic.movement.BoardMovement;
import chesstogether.chess.game.service.ChessGameService;
import org.springframework.stereotype.Service;

@Service
public class ChessGameServiceImpl implements ChessGameService {
    private Board board;
    private final BoardMovement boardMovement;

    public ChessGameServiceImpl(BoardMovement boardMovement) {
        this.boardMovement = boardMovement;
    }

    @Override
    public Board getGameBoard(String x1, String y1, String x2, String y2) {
        Move playerMove = generateMove(x1, y1, x2, y2);
        if (playerMove != null) {
            board = boardMovement.makeMoveOnBoard(board, playerMove);

        } else {
            board = DefaultBoardGenerator.generateDefaultBoard();
        }
        return board;
    }

    private Move generateMove(String x1, String y1, String x2, String y2) {
        if (x1 == null || y1 == null || x2 == null || y2 == null) {
            return null;
        }
        try {
            Move move = new Move();
            move.setX1(Integer.parseInt(x1));
            move.setY1(Integer.parseInt(y1));
            move.setX2(Integer.parseInt(x2));
            move.setY2(Integer.parseInt(y2));
            return move;
        } catch (Exception e) {
            return null;
        }
    }
}
