package Model.Pieces;
import Model.Team;
import Model.Type;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Felix Kaasa
 */
public class testCreatePieces {

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
    public void createKing(){
        King whiteKing = new King(Team.WHITE);
        Assert.assertEquals(Team.WHITE, whiteKing.team);
        Assert.assertEquals(Type.KING, whiteKing.type);

        King blackKing = new King(Team.BLACK);
        Assert.assertEquals(Team.BLACK, blackKing.team);
        Assert.assertEquals(Type.KING, blackKing.type);

        Pawn whitePawn = new Pawn(Team.WHITE);
        Assert.assertNotEquals(Type.KING, whitePawn.type);
    }

    @Test
    public void createKnight(){
        Knight whiteKnight = new Knight(Team.WHITE);
        Assert.assertEquals(Team.WHITE, whiteKnight.team);
        Assert.assertEquals(Type.KNIGHT, whiteKnight.type);

        Knight blackKnight = new Knight(Team.BLACK);
        Assert.assertEquals(Team.BLACK, blackKnight.team);
        Assert.assertEquals(Type.KNIGHT, blackKnight.type);

        Pawn whitePawn = new Pawn(Team.WHITE);
        Assert.assertNotEquals(Type.KNIGHT, whitePawn.type);
    }

    @Test
    public void createBishop(){
        Bishop whiteBishop = new Bishop(Team.WHITE);
        Assert.assertEquals(Team.WHITE, whiteBishop.team);
        Assert.assertEquals(Type.BISHOP, whiteBishop.type);

        Bishop blackBishop = new Bishop(Team.BLACK);
        Assert.assertEquals(Team.BLACK, blackBishop.team);
        Assert.assertEquals(Type.BISHOP, blackBishop.type);

        Pawn whitePawn = new Pawn(Team.WHITE);
        Assert.assertNotEquals(Type.BISHOP, whitePawn.type);
    }

    @Test
    public void createRook(){
        Rook whiteRook = new Rook(Team.WHITE);
        Assert.assertEquals(Team.WHITE, whiteRook.team);
        Assert.assertEquals(Type.ROOK, whiteRook.type);

        Rook blackRook = new Rook(Team.BLACK);
        Assert.assertEquals(Team.BLACK, blackRook.team);
        Assert.assertEquals(Type.ROOK, blackRook.type);

        Pawn whitePawn = new Pawn(Team.WHITE);
        Assert.assertNotEquals(Type.ROOK, whitePawn.type);
    }

}
