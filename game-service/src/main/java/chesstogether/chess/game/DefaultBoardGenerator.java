package chesstogether.chess.game;

import chesstogether.chess.game.utils.Player;
import chesstogether.chess.game.utils.Type;

import java.util.ArrayList;
import java.util.List;

import static chesstogether.chess.game.utils.Type.*;

public class DefaultBoardGenerator {
    private static final int BOARD_SIZE = 8;
    private static final int ALL_SQUARES_COUNT = 64;

    public static Board generateDefaultBoard() {
        List<Square> squares = generateDefaultSquares();

        List<Move> moves = generateDefaultMoves();
        //Create a list of pieces that can be attained when
        //a pawn reaches the back row
        List<Type> promotionPieces = generatePromotionPieces();

        Board board = new Board();
        board.setActivePlayer(Player.WHITE);
        board.setSquares(squares);
        board.setMoves(moves);
        board.setPromotionPieces(promotionPieces);
        return board;
    }

    private static List<Square> generateDefaultSquares() {
        List<Square> squares = new ArrayList<>(ALL_SQUARES_COUNT);

        Type[] backRowPieces = {ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK};
        Type frontRowPieces = PAWN;
        // Create the back row of White
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = new Piece(Player.WHITE, backRowPieces[i], false, false);
            squares.add(new Square(1, i + 1, piece));
        }
        // Create the front row of White (all pawns)
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = new Piece(Player.WHITE, frontRowPieces, false, false);
            squares.add(new Square(2, i + 1, piece));
        }
        // Create the empty squares in the middle of the board
        for (int i = 3; i <= 6; i++) {
            for (int j = 1; j <= 8; j++) {
                squares.add(new Square(i, j, null));
            }
        }
        // Create the front row of Black (all pawns)
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = new Piece(Player.BLACK, frontRowPieces, false, false);
            squares.add(new Square(7, i + 1, piece));
        }
        // Create the back row of Black
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece piece = new Piece(Player.BLACK, backRowPieces[i], false, false);
            squares.add(new Square(8, i + 1, piece));
        }
        return squares;
    }

    private static List<Move> generateDefaultMoves() {
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
        return moves;
    }

    private static List<Type> generatePromotionPieces() {
        //A pawn can be promoted to a Rook, Knight, Bishop, or Queen
        List<Type> promotionPieces = new ArrayList<>();
        promotionPieces.add(ROOK);
        promotionPieces.add(KNIGHT);
        promotionPieces.add(BISHOP);
        promotionPieces.add(QUEEN);
        return promotionPieces;
    }
}
