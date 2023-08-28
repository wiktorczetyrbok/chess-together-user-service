package com.myApp.web.game;

import java.util.ArrayList;
import java.util.List;

public class DefaultBoardGenerator {
    public Board generateDefaultBoard() {
        List<Square> squares = generateDefaultSquares();
        //Create a list of legal first moves
        List<Move> moves = generateDefaultMoves();
        //Create a list of pieces that can be attained when
        //a pawn reaches the back row
        List<String> promotionPieces = generatePromotionPieces();
        Board board = new Board();
        board.setActivePlayer("White");
        board.setSquares(squares);
        board.setMoves(moves);
        board.setPromotionPieces(promotionPieces);
        return board;
    }

    private List<Square> generateDefaultSquares() {
        List<Square> squares = new ArrayList<>(64);

        String[] backRowPieces = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};
        String[] frontRowPieces = {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"};
        // Create the back row of White
        for (int i = 0; i < backRowPieces.length; i++) {
            squares.add(new Square(1, i + 1, new Piece("White", backRowPieces[i], false)));
        }
        // Create the front row of White (all pawns)
        for (int i = 0; i < frontRowPieces.length; i++) {
            squares.add(new Square(2, i + 1, new Piece("White", frontRowPieces[i], false)));
        }
        // Create the empty squares in the middle of the board
        for (int i = 3; i <= 6; i++) {
            for (int j = 1; j <= 8; j++) {
                squares.add(new Square(i, j, null));
            }
        }
        // Create the front row of Black (all pawns)
        for (int i = 0; i < frontRowPieces.length; i++) {
            squares.add(new Square(7, i + 1, new Piece("Black", frontRowPieces[i], false)));
        }
        // Create the back row of Black
        for (int i = 0; i < backRowPieces.length; i++) {
            squares.add(new Square(8, i + 1, new Piece("Black", backRowPieces[i], false)));
        }
        return squares;
    }

    private List<Move> generateDefaultMoves() {
        //Create a list of legal moves
        List<Move> moves = new ArrayList<>();
        //Pawns can move forward one or two
        for (int i = 1; i <= 8; i++) {
            moves.add(new Move(2, i, 3, i));
            moves.add(new Move(2, i, 4, i));
        }
        //The left knight can move forward left and right
        moves.add(new Move(1, 2, 3, 1));
        moves.add(new Move(1, 2, 3, 3));
        //The right knight can move forward left and right
        moves.add(new Move(1, 7, 3, 6));
        moves.add(new Move(1, 7, 3, 8));
        //Return a list of the legal moves
        return moves;
    }

    private List<String> generatePromotionPieces() {
        //A pawn can be promoted to a Rook, Knight, Bishop, or Queen
        List<String> promotionPieces = new ArrayList<>();
        promotionPieces.add("Rook");
        promotionPieces.add("Knight");
        promotionPieces.add("Bishop");
        promotionPieces.add("Queen");
        return promotionPieces;
    }
}
