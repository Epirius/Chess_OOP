package Model;

import Model.Pieces.Queen;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Felix Kaasa
 */
public class testMove {

    @Test
    public void testingCreatingMove(){
        Move move = new Move(2,6);
        int[] test = new int[]{2,6};
        Assert.assertEquals(test[0], move.getMove()[0]);
        Assert.assertEquals(test[1], move.getMove()[1]);
    }

    @Test
    public void testDoMove(){
        Board board = new Board(false);
        Queen q1 = new Queen(Team.BLACK);
        board.getSquare(30).setPiece(q1);
        List<Move> moves = q1.getPossibleMoves(30, board);
        board.doMove(moves.get(0));
        Assert.assertEquals(q1, board.getSquare(22).getPiece());
        Assert.assertTrue(board.getSquare(30).isEmpty());
    }
}
