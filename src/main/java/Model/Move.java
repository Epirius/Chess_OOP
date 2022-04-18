package Model;

import java.util.Objects;

/**
 * @author Felix Kaasa
 */

public class Move {
    public final int from;
    public final int to;
    private boolean castle = false;
    public  Move castleRookMove;
    boolean enPassant = false;
    public int enPassantPosition;
    public boolean isMinimaxTestMove = false;



    /**
     * default move constructor (using square id)
     * @param from int id of the square the piece is moving from
     * @param to int id of the square the piece is moving to
     */
    public Move(int from, int to){
        this.from = from;
        this.to = to;
    }


    /**
     * default move constructor. (using coordinates)
     * @param from int[x,y] coordinates of the square the piece is moving from
     * @param to int[x,y] coordinates of the square the piece is moving to
     */
    public Move(int[] from, int[] to){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
    }

    /**
     * alternative constructor used for castling move (using square id)
     * @param from int id of the square the piece is moving from
     * @param to int id of the square the piece is moving to
     * @param castleRookMove the move of the rook that will be moved during castling
     */
    public Move(int from, int to, Move castleRookMove){
        this.from = from;
        this.to = to;
        this.castle = true;
        this.castleRookMove = castleRookMove;
    }

    /**
     * alternative constructor used for castling move (using coordintates)
     * @param from int[x,y] coordinates of the square the piece is moving from
     * @param to int[x,y] coordinates of the square the piece is moving to
     * @param castleRookMove the move of the rook that will be moved during castling
     */
    public Move(int[] from, int[] to, Move castleRookMove){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
        this.castle = true;
        this.castleRookMove = castleRookMove;
    }

    /**
     * * alternative constructor used for en passant move (using square id)
     * @param from int id of the square the piece is moving from
     * @param to int id of the square the piece is moving to
     * @param enPassant square id of the piece that is being captured.
     */
    public Move(int from, int to, int enPassant){
        this.from = from;
        this.to = to;
        this.enPassant = true;
        this.enPassantPosition = enPassant;
    }

    /**
     * alternative constructor used for en passant move (using coordintates)
     * @param from int[x,y] coordinates of the square the piece is moving from
     * @param to int[x,y] coordinates of the square the piece is moving to
     * @param enPassant coordinates of the square that is being captured
     */
    public Move(int[] from, int[] to, int[] enPassant){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
        this.enPassant = true;
        this.enPassantPosition = IBoard.coordinatesToSquare(enPassant);
    }

    /**
     * @return true if the move is a castling move.
     */
    public boolean isMoveCastle(){
        return castle;
    }

    public boolean isEnPassant(){
        return enPassant;
    }

    public int[] getMove(){
        return new int[]{from, to};
    }

    public String toString(){
        if (castle){
            return "from: " + this.from + " to: " + this.to + " - Castling = true";
        }
        if (enPassant){
            return "from: " + this.from + " to: " + this.to + " - en passant: True - " + enPassantPosition;
        }
        return "from: " + this.from + " to: " + this.to;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Move move = (Move) that;
        return from == move.from && to == move.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
