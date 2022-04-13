package Model;

import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Felix Kaasa
 */
public class testSquare {

    @Test
    public void createSquare(){
        Square square = new Square();
        Assert.assertEquals(0, square.getSquareId());
        Square.resetNumberOfSquares();
    }

    @Test
    public void putPieceInSquare(){
        Square square = new Square();

        Queen queen = new Queen(Team.WHITE);
        square.setPiece(queen);
        Assert.assertEquals(queen, square.getPiece());

        square.removePiece();
        Assert.assertEquals(null, square.getPiece());

        Pawn pawn = new Pawn(Team.WHITE);
        square.setPiece(pawn);
        Assert.assertEquals(pawn, square.getPiece());
        Square.resetNumberOfSquares();
    }

    @Test
    public void emptySquareReturnsNull(){
        Board board = new Board(false);
        Assert.assertNull(board.getSquare(0).getPiece());

    }
}
