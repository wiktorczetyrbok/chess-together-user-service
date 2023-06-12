package com.myApp.web.game.logic.impl;

import com.myApp.web.game.Move;
import com.myApp.web.game.Piece;
import com.myApp.web.game.Square;
import com.myApp.web.game.logic.*;
import com.myApp.web.game.physics.SquareLocation;
import com.myApp.web.game.physics.SquareStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("boardMoveGeneratorImpl")
public class BoardMovementGeneratorImpl implements BoardMovementGenerator {
    @Autowired
    private StatusMapFactory statusMapFactory;
    @Autowired
    private ActivePieceExtractor activePieceExtractor;
    @Autowired
    private SquareLocationFactory squareLocationFactory;
    @Autowired
    private MoveCalcFactory moveCalculatorFactory;

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
