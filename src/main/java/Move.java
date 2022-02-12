import java.util.Objects;

/**
 * @author Felix Kaasa
 */

public class Move {
    public final int from;
    public final int to;
    private boolean castle = false;
    boolean enPassant = false;
    public int enPassantPosition;

    public Move(int from, int to){
        this.from = from;
        this.to = to;
    }

    public Move(int[] from, int[] to){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
    }


    //these two constructors below are used by the king to track castling.
    public Move(int from, int to, boolean castle){
        this.from = from;
        this.to = to;
        this.castle = castle;
    }

    public Move(int[] from, int[] to, boolean castle){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
        this.castle = castle;
    }

    // these two constructors below are used for en passant.
    public Move(int from, int to, int enPassant){
        this.from = from;
        this.to = to;
        this.enPassant = true;
        this.enPassantPosition = enPassant;
    }

    public Move(int[] from, int[] to, int[] enPassant){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
        this.enPassant = true;
        this.enPassantPosition = IBoard.coordinatesToSquare(enPassant);
    }

    public int[] getMove(){
        return new int[]{from, to};
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return from == move.from && to == move.to && castle == move.castle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, castle);
    }
}
