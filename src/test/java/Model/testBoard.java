package Model;

import Model.Pieces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Felix Kaasa
 */
public class testBoard {

    @Test
    public void createBoard(){
        Board board = new Board(false);
        Square square = board.getSquare(63);
        Assert.assertEquals(63, square.getSquareId());

        Queen queen = new Queen(Team.WHITE);
        square.setPiece(queen);
        Assert.assertEquals(queen, board.getPiece(63));
    }

    @Test
    public void testBoardInit(){
        Board board = new Board();
        Assert.assertTrue(board.getSquare(0).getPiece().type == Type.ROOK);
        Assert.assertTrue(board.getSquare(1).getPiece().type == Type.KNIGHT);
        Assert.assertTrue(board.getSquare(2).getPiece().type == Type.BISHOP);
        Assert.assertTrue(board.getSquare(3).getPiece().type == Type.QUEEN);
        Assert.assertTrue(board.getSquare(4).getPiece().type == Type.KING);
        Assert.assertTrue(board.getSquare(5).getPiece().type == Type.BISHOP);
        Assert.assertTrue(board.getSquare(6).getPiece().type == Type.KNIGHT);
        Assert.assertTrue(board.getSquare(7).getPiece().type == Type.ROOK);

        Assert.assertTrue(board.getSquare(56).getPiece().type == Type.ROOK);
        Assert.assertTrue(board.getSquare(57).getPiece().type == Type.KNIGHT);
        Assert.assertTrue(board.getSquare(58).getPiece().type == Type.BISHOP);
        Assert.assertTrue(board.getSquare(59).getPiece().type == Type.QUEEN);
        Assert.assertTrue(board.getSquare(60).getPiece().type == Type.KING);
        Assert.assertTrue(board.getSquare(61).getPiece().type == Type.BISHOP);
        Assert.assertTrue(board.getSquare(62).getPiece().type == Type.KNIGHT);
        Assert.assertTrue(board.getSquare(63).getPiece().type == Type.ROOK);

        for (int i = 0; i < 8; i++) {
            Assert.assertTrue(board.getSquare(i + 8).getPiece().type == Type.PAWN);
            Assert.assertTrue(board.getSquare(i + 48).getPiece().type == Type.PAWN);
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

            Assert.assertTrue(board.getSquare(0).getPiece().team == team);
            Assert.assertTrue(board.getSquare(1).getPiece().team == team);
            Assert.assertTrue(board.getSquare(2).getPiece().team == team);
            Assert.assertTrue(board.getSquare(3).getPiece().team == team);
            Assert.assertTrue(board.getSquare(4).getPiece().team == team);
            Assert.assertTrue(board.getSquare(5).getPiece().team == team);

            Assert.assertTrue(board.getSquare(0).getPiece().type == Type.PAWN);
            Assert.assertTrue(board.getSquare(1).getPiece().type == Type.ROOK);
            Assert.assertTrue(board.getSquare(2).getPiece().type == Type.KNIGHT);
            Assert.assertTrue(board.getSquare(3).getPiece().type == Type.BISHOP);
            Assert.assertTrue(board.getSquare(4).getPiece().type == Type.QUEEN);
            Assert.assertTrue(board.getSquare(5).getPiece().type == Type.KING);
        }
    }

    @Test
    public void squareToCoords() {
        Board board = new Board(false);
        int[] coord = IBoard.squareToCoordinates(16);
        int[] test = new int[]{0,2};
        Assert.assertEquals(Arrays.toString(test), Arrays.toString(coord));
    }

    @Test
    public void coordsToSquare(){
        Board board = new Board(false);
        int[] coord = new int[]{1,1};
        int square = IBoard.coordinatesToSquare(coord);
        Assert.assertEquals(9, square);
        Assert.assertEquals(square, board.getSquare(square).getSquareId());
    }

}
