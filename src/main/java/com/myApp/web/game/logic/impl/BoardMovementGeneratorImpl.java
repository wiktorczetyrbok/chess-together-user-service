package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Move;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.*;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("boardMoveGeneratorImpl")
public class BoardMovementGeneratorImpl implements BoardMovementGenerator {
    private final StatusMapFactory statusMapFactory;
    private final ActivePieceExtractor activePieceExtractor;
    private final SquareLocationFactory squareLocationFactory;
    private final MoveCalcFactory moveCalculatorFactory;

    public BoardMovementGeneratorImpl(StatusMapFactory statusMapFactory,
                                      ActivePieceExtractor activePieceExtractor,
                                      SquareLocationFactory squareLocationFactory,
                                      MoveCalcFactory moveCalculatorFactory) {
        this.statusMapFactory = statusMapFactory;
        this.activePieceExtractor = activePieceExtractor;
        this.squareLocationFactory = squareLocationFactory;
        this.moveCalculatorFactory = moveCalculatorFactory;
    }

    @Override
    public List<Move> generatePossibleMoves(String activePlayer, List<Square> squares) {
        Map<SquareLocation, SquareStatus> locationStatusMap = statusMapFactory.buildStatusMap(activePlayer, squares);
        List<Move> moves = new ArrayList<>();
        List<Square> squaresWithActivePieces = activePieceExtractor.extractSquaresWithActivePieces(activePlayer, squares);
        for (Square square : squaresWithActivePieces) {
            SquareLocation pieceLocation = squareLocationFactory.buildSquareLocation(square);
            Piece piece = square.getPiece();
            MoveCalc moveCalculator = moveCalculatorFactory.buildMoveCalculator(piece);
            List<Move> movesWithPiece = moveCalculator.getPossibleMoves(pieceLocation, locationStatusMap);
            moves.addAll(movesWithPiece);
        }
        return moves;

    }
}
