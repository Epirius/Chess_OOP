package Model;

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

    //default
    public Move(int from, int to){
        this.from = from;
        this.to = to;
    }

    //alternative for the default with int array (x,y)
    public Move(int[] from, int[] to){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
    }


    //used by the king to track castling.
    public Move(int from, int to, boolean castle){
        this.from = from;
        this.to = to;
        this.castle = castle;
    }

    //used by the king to track castling.
    public Move(int[] from, int[] to, boolean castle){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
        this.castle = castle;
    }

    //used for en passant.
    public Move(int from, int to, int enPassant){
        this.from = from;
        this.to = to;
        this.enPassant = true;
        this.enPassantPosition = enPassant;
    }

    //used for en passant.
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
