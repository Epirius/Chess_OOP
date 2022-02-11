import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**
 * @author Felix Kaasa
 */

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
    public void testingPluss() throws Exception {
        List<Integer> l = PlussMoves.testingPluss(19);
        int[] ints = {11, 3, 18, 17, 16, 20, 21, 22, 23, 27, 35, 43, 51, 59};
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

    @Test
    public void testKingEdgeMovement(){
        Board board = new Board();
        King k1 = new King(Team.WHITE);
        King k2 = new King(Team.WHITE);
        King k3 = new King(Team.WHITE);
        King k4 = new King(Team.WHITE);
        King k5 = new King(Team.WHITE);
        King k6 = new King(Team.WHITE);
        King k7 = new King(Team.WHITE);
        King k8 = new King(Team.WHITE);
        King k9 = new King(Team.WHITE);

        k1.setCastleKingSideToFalse();
        k1.setCastleQueenSideToFalse();
        k2.setCastleKingSideToFalse();
        k2.setCastleQueenSideToFalse();
        k3.setCastleKingSideToFalse();
        k3.setCastleQueenSideToFalse();
        k4.setCastleKingSideToFalse();
        k4.setCastleQueenSideToFalse();
        k5.setCastleKingSideToFalse();
        k5.setCastleQueenSideToFalse();
        k6.setCastleKingSideToFalse();
        k6.setCastleQueenSideToFalse();
        k7.setCastleKingSideToFalse();
        k7.setCastleQueenSideToFalse();
        k8.setCastleKingSideToFalse();
        k8.setCastleQueenSideToFalse();
        k9.setCastleKingSideToFalse();
        k9.setCastleQueenSideToFalse();

        board.getSquare(0).setPiece(k1);
        board.getSquare(7).setPiece(k2);
        board.getSquare(56).setPiece(k3);
        board.getSquare(63).setPiece(k4);
        board.getSquare(3).setPiece(k5);
        board.getSquare(39).setPiece(k6);
        board.getSquare(32).setPiece(k7);
        board.getSquare(59).setPiece(k8);
        board.getSquare(35).setPiece(k9);

        List<Move> t1 = k1.getPossibleMoves(0, board);
        List<Move> t2 = k2.getPossibleMoves(7, board);
        List<Move> t3 = k3.getPossibleMoves(56, board);
        List<Move> t4 = k4.getPossibleMoves(63, board);
        List<Move> t5 = k5.getPossibleMoves(3, board);
        List<Move> t6 = k6.getPossibleMoves(39, board);
        List<Move> t7 = k7.getPossibleMoves(32, board);
        List<Move> t8 = k8.getPossibleMoves(59, board);
        List<Move> t9 = k9.getPossibleMoves(35, board);

        Assert.assertEquals(1, t1.get(0).to);
        Assert.assertEquals(8, t1.get(1).to);
        Assert.assertEquals(9, t1.get(2).to);
        Assert.assertTrue(t1.size() == 3);

        Assert.assertEquals(6, t2.get(0).to);
        Assert.assertEquals(14, t2.get(1).to);
        Assert.assertEquals(15, t2.get(2).to);
        Assert.assertTrue(t2.size() == 3);

        Assert.assertEquals(49, t3.get(0).to);
        Assert.assertEquals(48, t3.get(1).to);
        Assert.assertEquals(57, t3.get(2).to);
        Assert.assertTrue(t3.size() == 3);

        Assert.assertEquals(55, t4.get(0).to);
        Assert.assertEquals(54, t4.get(1).to);
        Assert.assertEquals(62, t4.get(2).to);
        Assert.assertTrue(t4.size() == 3);

        Assert.assertEquals(2, t5.get(0).to);
        Assert.assertEquals(4, t5.get(1).to);
        Assert.assertEquals(10, t5.get(2).to);
        Assert.assertEquals(11, t5.get(3).to);
        Assert.assertEquals(12, t5.get(4).to);
        Assert.assertTrue(t5.size() == 5);

        Assert.assertEquals(31, t6.get(0).to);
        Assert.assertEquals(30, t6.get(1).to);
        Assert.assertEquals(38, t6.get(2).to);
        Assert.assertEquals(46, t6.get(3).to);
        Assert.assertEquals(47, t6.get(4).to);
        Assert.assertTrue(t6.size() == 5);

        Assert.assertEquals(25, t7.get(0).to);
        Assert.assertEquals(24, t7.get(1).to);
        Assert.assertEquals(33, t7.get(2).to);
        Assert.assertEquals(40, t7.get(3).to);
        Assert.assertEquals(41, t7.get(4).to);
        Assert.assertTrue(t7.size() == 5);

        Assert.assertEquals(52, t8.get(0).to);
        Assert.assertEquals(51, t8.get(1).to);
        Assert.assertEquals(50, t8.get(2).to);
        Assert.assertEquals(58, t8.get(3).to);
        Assert.assertEquals(60, t8.get(4).to);
        Assert.assertTrue(t8.size() == 5);

        Assert.assertEquals(28, t9.get(0).to);
        Assert.assertEquals(27, t9.get(1).to);
        Assert.assertEquals(26, t9.get(2).to);
        Assert.assertEquals(34, t9.get(3).to);
        Assert.assertEquals(36, t9.get(4).to);
        Assert.assertEquals(42, t9.get(5).to);
        Assert.assertEquals(43, t9.get(6).to);
        Assert.assertEquals(44, t9.get(7).to);
        Assert.assertTrue(t9.size() == 8);


    }

    @Test
    public void testKingBlockedMovement(){
        Board board = new Board();
        King whiteKing = new King(Team.WHITE);
        Knight whiteKnight = new Knight(Team.WHITE);
        Knight blackKnight = new Knight(Team.BLACK);
        whiteKing.setCastleKingSideToFalse();
        whiteKing.setCastleQueenSideToFalse();


        board.getSquare(0).setPiece(whiteKing);
        board.getSquare(1).setPiece(whiteKnight);
        board.getSquare(8).setPiece(blackKnight);

        List<Move> t1 = whiteKing.getPossibleMoves(0, board);
        Assert.assertEquals(8, t1.get(0).to);
        Assert.assertEquals(9, t1.get(1).to);
        Assert.assertTrue(t1.size() == 2);
    }

    @Test
    public void testWhitePawnMovement(){
        Board board = new Board();

        Pawn p1 = new Pawn(Team.WHITE);
        Pawn p5 = new Pawn(Team.BLACK);
        Pawn p6 = new Pawn(Team.BLACK);
        board.getSquare(11).setPiece(p1);
        List<Move> t1 = p1.getPossibleMoves(11, board);
        Assert.assertEquals(new Move(11, 19), t1.get(0));
        Assert.assertEquals(new Move(11, 27), t1.get(1));
        Assert.assertEquals(2, t1.size());
        board.getSquare(27).setPiece(p5);
        t1 = p1.getPossibleMoves(11, board);
        Assert.assertEquals(1, t1.size());
        Assert.assertEquals(new Move(11, 19), t1.get(0));
        board.getSquare(19).setPiece(p6);
        t1 = p1.getPossibleMoves(11, board);
        Assert.assertEquals(0, t1.size());

        Pawn p2 = new Pawn(Team.WHITE);
        board.getSquare(22).setPiece(p2);
        List<Move> t2 = p2.getPossibleMoves(22, board);
        Assert.assertEquals(new Move(22, 30), t2.get(0));
        Assert.assertEquals(1, t2.size());

        Pawn p3 = new Pawn(Team.BLACK);
        Pawn p4 = new Pawn(Team.BLACK);
        board.getSquare(29).setPiece(p3);
        t2 = p2.getPossibleMoves(22, board);
        Assert.assertEquals(2, t2.size());
        Assert.assertEquals(new Move(22, 29), t2.get(0));
        board.getSquare(31).setPiece(p4);
        t2 = p2.getPossibleMoves(22, board);
        Assert.assertEquals(3, t2.size());
        Assert.assertEquals(new Move(22, 31), t2.get(1));
        Assert.assertEquals(new Move(22, 30), t2.get(2));
    }

    @Test
    public void testBlackPawnMovement(){
        Board board = new Board();

        Pawn p1 = new Pawn(Team.BLACK);
        Pawn p5 = new Pawn(Team.WHITE);
        Pawn p6 = new Pawn(Team.WHITE);

        board.getSquare(51).setPiece(p1);
        List<Move> t1 = p1.getPossibleMoves(51, board);
        Assert.assertEquals(new Move(51, 43), t1.get(0));
        Assert.assertEquals(new Move(51, 35), t1.get(1));
        Assert.assertEquals(2, t1.size());
        board.getSquare(35).setPiece(p5);
        t1 = p1.getPossibleMoves(51, board);
        Assert.assertEquals(1, t1.size());
        Assert.assertEquals(new Move(51, 43), t1.get(0));
        board.getSquare(43).setPiece(p6);
        t1 = p1.getPossibleMoves(51, board);
        Assert.assertEquals(0, t1.size());

        Pawn p2 = new Pawn(Team.BLACK);
        board.getSquare(46).setPiece(p2);
        List<Move> t2 = p2.getPossibleMoves(46, board);
        Assert.assertEquals(new Move(46, 38), t2.get(0));
        Assert.assertEquals(1, t2.size());


        Pawn p3 = new Pawn(Team.WHITE);
        Pawn p4 = new Pawn(Team.WHITE);
        board.getSquare(39).setPiece(p3);
        t2 = p2.getPossibleMoves(46, board);
        Assert.assertEquals(2, t2.size());
        Assert.assertEquals(new Move(46, 39), t2.get(0));
        board.getSquare(37).setPiece(p4);
        t2 = p2.getPossibleMoves(46, board);
        Assert.assertEquals(3, t2.size());
        Assert.assertEquals(new Move(46, 37), t2.get(0));
        Assert.assertEquals(new Move(46, 38), t2.get(2));
    }
}
