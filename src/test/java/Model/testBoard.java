package Model;

import Model.Pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Felix Kaasa
 */
public class testBoard {

    @Test
    public void createBoard(){
        Board board = new Board(false);
        Square<Piece> square = board.getSquare(63);
        Assertions.assertEquals(63, square.getSquareId());

        Queen queen = new Queen(Team.WHITE);
        square.setPiece(queen);
        Assertions.assertEquals(queen, board.getPiece(63));
    }

    @Test
    public void testBoardInit(){
        Board board = new Board();
        Assertions.assertTrue(board.getSquare(0).getPiece().type == Type.ROOK);
        Assertions.assertTrue(board.getSquare(1).getPiece().type == Type.KNIGHT);
        Assertions.assertTrue(board.getSquare(2).getPiece().type == Type.BISHOP);
        Assertions.assertTrue(board.getSquare(3).getPiece().type == Type.QUEEN);
        Assertions.assertTrue(board.getSquare(4).getPiece().type == Type.KING);
        Assertions.assertTrue(board.getSquare(5).getPiece().type == Type.BISHOP);
        Assertions.assertTrue(board.getSquare(6).getPiece().type == Type.KNIGHT);
        Assertions.assertTrue(board.getSquare(7).getPiece().type == Type.ROOK);

        Assertions.assertTrue(board.getSquare(56).getPiece().type == Type.ROOK);
        Assertions.assertTrue(board.getSquare(57).getPiece().type == Type.KNIGHT);
        Assertions.assertTrue(board.getSquare(58).getPiece().type == Type.BISHOP);
        Assertions.assertTrue(board.getSquare(59).getPiece().type == Type.QUEEN);
        Assertions.assertTrue(board.getSquare(60).getPiece().type == Type.KING);
        Assertions.assertTrue(board.getSquare(61).getPiece().type == Type.BISHOP);
        Assertions.assertTrue(board.getSquare(62).getPiece().type == Type.KNIGHT);
        Assertions.assertTrue(board.getSquare(63).getPiece().type == Type.ROOK);

        for (int i = 0; i < 8; i++) {
            Assertions.assertTrue(board.getSquare(i + 8).getPiece().type == Type.PAWN);
            Assertions.assertTrue(board.getSquare(i + 48).getPiece().type == Type.PAWN);
        }
    }

    @Test
    public void  testPlacePiece(){
        for (Team team : Team.values()) {
            Board board = new Board(false);
            board.getSquare(0).setPiece(new Pawn(team));
            board.getSquare(1).setPiece(new Rook(team));
            board.getSquare(2).setPiece(new Knight(team));
            board.getSquare(3).setPiece(new Bishop(team));
            board.getSquare(4).setPiece(new Queen(team));
            board.getSquare(5).setPiece(new King(team));

            Assertions.assertTrue(board.getSquare(0).getPiece().team == team);
            Assertions.assertTrue(board.getSquare(1).getPiece().team == team);
            Assertions.assertTrue(board.getSquare(2).getPiece().team == team);
            Assertions.assertTrue(board.getSquare(3).getPiece().team == team);
            Assertions.assertTrue(board.getSquare(4).getPiece().team == team);
            Assertions.assertTrue(board.getSquare(5).getPiece().team == team);

            Assertions.assertTrue(board.getSquare(0).getPiece().type == Type.PAWN);
            Assertions.assertTrue(board.getSquare(1).getPiece().type == Type.ROOK);
            Assertions.assertTrue(board.getSquare(2).getPiece().type == Type.KNIGHT);
            Assertions.assertTrue(board.getSquare(3).getPiece().type == Type.BISHOP);
            Assertions.assertTrue(board.getSquare(4).getPiece().type == Type.QUEEN);
            Assertions.assertTrue(board.getSquare(5).getPiece().type == Type.KING);
        }
    }

    @Test
    public void squareToCoords() {
        Board board = new Board(false);
        int[] coord = IBoard.squareToCoordinates(16);
        int[] test = new int[]{0,2};
        Assertions.assertEquals(Arrays.toString(test), Arrays.toString(coord));
    }

    @Test
    public void coordsToSquare(){
        Board board = new Board(false);
        int[] coord = new int[]{1,1};
        int square = IBoard.coordinatesToSquare(coord);
        Assertions.assertEquals(9, square);
        Assertions.assertEquals(square, board.getSquare(square).getSquareId());
    }

    @Test
    public void testUndoMove(){
        Board board = new Board(true);
        board.doMove(new Move(8,14));
        Assertions.assertEquals(new Move(8,14), board.moveHistoryList.peek().move);
        board.doMove(new Move(48,40));
        Assertions.assertEquals(new Move(48,40), board.moveHistoryList.peek().move);
        board.undoMove(1);
        Assertions.assertEquals(new Move(8,14), board.moveHistoryList.peek().move);
    }

    @Test
    public void testLoadBoardFromMoveHistory(){
        Board board = new Board(true);
        Assertions.assertFalse(board.getSquare(8).isEmpty());
        Assertions.assertFalse(board.getSquare(48).isEmpty());
        board.doMove(new Move(8,16));
        board.doMove(new Move(48,40));
        Assertions.assertTrue(board.getSquare(8).isEmpty());
        Assertions.assertTrue(board.getSquare(48).isEmpty());

        board.loadBoardFromMoveHistory(board.moveHistoryList.get(0));
        Assertions.assertFalse(board.getSquare(8).isEmpty());
        Assertions.assertFalse(board.getSquare(48).isEmpty());
    }

    @Test
    public void testIsSquareFriendly(){
        Board board = new Board(true);
        Assertions.assertTrue(board.isSquareFriendly(8));
        Assertions.assertFalse(board.isSquareFriendly(48));

        board.doMove(new Move(9,17));

        Assertions.assertFalse(board.isSquareFriendly(8));
        Assertions.assertTrue(board.isSquareFriendly(48));

    }
}
