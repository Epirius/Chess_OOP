import org.junit.Assert;
import org.junit.Test;

public class test {
    @Test
    public void createPawn(){
        Pawn whitePawn = new Pawn(Team.WHITE);
        Assert.assertEquals(Team.WHITE, whitePawn.team);
        Assert.assertEquals(Type.PAWN, whitePawn.type);

        Pawn blackPawn = new Pawn(Team.BLACK);
        Assert.assertEquals(Team.BLACK, blackPawn.team);
        Assert.assertEquals(Type.PAWN, blackPawn.type);

        Queen whiteQueen = new Queen(Team.WHITE);
        Assert.assertNotEquals(Type.PAWN, whiteQueen.type);
    }

    @Test
    public void createQueen(){
        Queen whiteQueen = new Queen(Team.WHITE);
        Assert.assertEquals(Team.WHITE, whiteQueen.team);
        Assert.assertEquals(Type.QUEEN, whiteQueen.type);

        Queen blackQueen = new Queen(Team.BLACK);
        Assert.assertEquals(Team.BLACK, blackQueen.team);
        Assert.assertEquals(Type.QUEEN, blackQueen.type);

        Pawn whitePawn = new Pawn(Team.WHITE);
        Assert.assertNotEquals(Type.QUEEN, whitePawn.type);
    }

    @Test
    public void createSquare(){
        Square square = new Square(0);
        Assert.assertEquals(0, square.getSquareId());
    }

    @Test
    public void putPieceInSquare(){
        Square square = new Square(0);

        Queen queen = new Queen(Team.WHITE);
        square.setPiece(queen);
        Assert.assertEquals(queen, square.getPiece());

        square.removePiece();
        Assert.assertEquals(null, square.getPiece());

        Pawn pawn = new Pawn(Team.WHITE);
        square.setPiece(pawn);
        Assert.assertEquals(pawn, square.getPiece());
    }

    @Test
    public void createBoard(){
        Board board = new Board();
        Square square = board.getSquare(63);
        Assert.assertEquals(63, square.getSquareId());

        Queen queen = new Queen(Team.WHITE);
        square.setPiece(queen);
        Assert.assertEquals(queen, board.getPiece(63));
    }
}
