package com.myApp.web.controller;

import com.myApp.web.game.Board;
import com.myApp.web.game.DefaultBoardGenerator;
import com.myApp.web.game.Move;
import com.myApp.web.game.logic.movement.BoardMovement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chess")
@RestController
public class ChessBoardController {
    public Board board;
    private final BoardMovement boardMovement;

    public ChessBoardController(BoardMovement boardMovement) {
        this.boardMovement = boardMovement;
    }

    @RequestMapping("/game")
    public Board getGameState(
            @RequestParam(value = "x1", required = false) String x1,
            @RequestParam(value = "y1", required = false) String y1,
            @RequestParam(value = "x2", required = false) String x2,
            @RequestParam(value = "y2", required = false) String y2) {
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
