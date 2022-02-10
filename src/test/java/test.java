import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void squareToCoords() throws Exception {
        Board board = new Board();
        int[] coord = IBoard.squareToCoordinates(16);
        int[] test = new int[]{0,2};
        Assert.assertEquals(Arrays.toString(test), Arrays.toString(coord));
    }

    @Test
    public void coordsToSquare(){
        Board board = new Board();
        int[] coord = new int[]{1,1};
        int square = IBoard.coordinatesToSquare(coord);
        Assert.assertEquals(9, square);
        Assert.assertEquals(square, board.getSquare(square).getSquareId());
    }

    @Test
    public void emptySquareReturnsNull(){
        Board board = new Board();
        Assert.assertNull(board.getSquare(0).getPiece());

    }

    @Test
    public void testingDiagonal() throws Exception {
        List<Integer> l = DiagonalMoves.testingDiagonal(19);
        int[] ints = {12, 5, 10, 1, 26, 33, 40, 28, 37, 46, 55};
        List<Integer> test =  Arrays.stream(ints).boxed().toList();
        Assert.assertEquals(test, l);
    }

    @Test
    public void testingCreatingMove(){
        Move move = new Move(2,6);
        int[] test = new int[]{2,6};
        Assert.assertEquals(test[0], move.getMove()[0]);
        Assert.assertEquals(test[1], move.getMove()[1]);
    }
}
