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
        Board board = new Board(false);
        Square square = board.getSquare(63);
        Assert.assertEquals(63, square.getSquareId());

        Queen queen = new Queen(Team.WHITE);
        square.setPiece(queen);
        Assert.assertEquals(queen, board.getPiece(63));
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

    @Test
    public void emptySquareReturnsNull(){
        Board board = new Board(false);
        Assert.assertNull(board.getSquare(0).getPiece());

    }

    @Test
    public void testingDiagonal() {
        List<Integer> l = DiagonalMoves.testingDiagonal(19);
        int[] ints = {12, 5, 10, 1, 26, 33, 40, 28, 37, 46, 55};
        List<Integer> test =  Arrays.stream(ints).boxed().toList();
        Assert.assertEquals(test, l);
    }

    @Test
    public void testingPluss() {
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
        Board board = new Board(false);
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
        Board board = new Board(false);
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
        Board board = new Board(false);

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
        Board board = new Board(false);

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
    public void testDoMove(){
        Board board = new Board(false);
        Queen q1 = new Queen(Team.BLACK);
        board.getSquare(30).setPiece(q1);
        List<Move> moves = q1.getPossibleMoves(30, board);
        board.doMove(moves.get(0));
        Assert.assertEquals(q1, board.getSquare(22).getPiece());
        Assert.assertTrue(board.getSquare(30).isEmpty());
    }

    @Test
    public void testAllQueenMoves(){
        Board board = new Board(false);
        Queen q1 = new Queen(Team.BLACK);
        board.getSquare(30).setPiece(q1);
        List<Move> moves = q1.getPossibleMoves(30, board);
        int[] test = new int[]{22, 14, 6, 29, 28, 27, 26, 25, 24, 31, 38, 46, 54, 62, 23, 21, 12, 3, 37, 44, 51, 58, 39};
        for (int i = 0; i < test.length; i++) {
            boolean t = test[i] == moves.get(i).to;
            if (!t){System.out.println(test[i]);}
            Assert.assertEquals(test[i], moves.get(i).to);
        }
    }

    @Test
    public void testDoMoveAndKill(){
        Board board = new Board(false);
        Queen q1 = new Queen(Team.BLACK);
        Queen q2 = new Queen(Team.WHITE);
        board.getSquare(0).setPiece(q1);
        board.getSquare(1).setPiece(q2);

        Assert.assertEquals(q1, board.getSquare(0).getPiece());
        Assert.assertEquals(q2, board.getSquare(1).getPiece());

        board.doMove(new Move(0,1));

        Assert.assertTrue(board.getSquare(0).isEmpty());
        Assert.assertEquals(q1, board.getSquare(1).getPiece());

        board.doMove(new Move(1,0));

        Assert.assertTrue(board.getSquare(1).isEmpty());
        Assert.assertEquals(q1, board.getSquare(0).getPiece());
    }

    @Test
    public void testEnPassant(){
        Board board = new Board(false);
        Pawn p1 = new Pawn(Team.WHITE);
        Pawn p2 = new Pawn(Team.BLACK);

        board.getSquare(35).setPiece(p1);
        board.getSquare(52).setPiece(p2);
        board.doMove(new Move(52, 36));
        Move attack = new Move(35, 44, 36);
        Assert.assertTrue(p1.getPossibleMoves(35, board).contains(attack));

        board.doMove(attack);
        Assert.assertTrue(board.getSquare(35).isEmpty());
        Assert.assertTrue(board.getSquare(36).isEmpty());
        Assert.assertTrue(board.getSquare(52).isEmpty());
        Assert.assertEquals(p1, board.getSquare(44).getPiece());
        //todo test left side en passant
        //---------------------------------------------------------

        Pawn p3 = new Pawn(Team.WHITE);
        Pawn p4 = new Pawn(Team.BLACK);

        board.getSquare(12).setPiece(p3);
        board.getSquare(27).setPiece(p4);
        board.doMove(new Move(12, 28));
        Move attack2 = new Move(27, 20, 28);
        Assert.assertTrue(p4.getPossibleMoves(27, board).contains(attack2));

        board.doMove(attack2);
        Assert.assertTrue(board.getSquare(12).isEmpty());
        Assert.assertTrue(board.getSquare(27).isEmpty());
        Assert.assertTrue(board.getSquare(28).isEmpty());
        Assert.assertEquals(p4, board.getSquare(20).getPiece());
        //todo test left side en passant
    }

    @Test
    public void testSquaresBetween(){
        //need to test if board.squaresBetween works
        Assert.assertEquals(false, true);
    }

    /*
    @Test
    public void testGetPossibleMoves(){
        Board board = new Board();
        List<Move> moves = board.getPossibleMoves();
        Assert.assertEquals(22, moves.size());
        moves.remove(new Move(4,6,true));
        moves.remove(new Move(4,2,true));
        for (int i = 8; i < 16; i++) {
            moves.remove(new Move(i,i+8));
            moves.remove(new Move(i,i+16));
        }
        Assert.assertEquals(4, moves.size());
        moves.remove(new Move(1,16));
        moves.remove(new Move(1,18));
        moves.remove(new Move(6,21));
        moves.remove(new Move(6,23));
        Assert.assertEquals(0, moves.size());

    }
     */

    /*
    @Test
    public void testSquaresBetween(){
        Board board = new Board(false);
        List<Square> test = squaresBetween(0, 7, board);
        Assert.assertEquals(5, test.size());
        Queen q = new Queen(Team.WHITE);
        board.getSquare(3).setPiece(q);
        List<Square> test1 = squaresBetween(0, 7, board);
        Assert.assertEquals(0, test1.size());
    }*/
}
