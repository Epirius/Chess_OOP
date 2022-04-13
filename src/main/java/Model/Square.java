package Model;

import Model.Pieces.Piece;

/**
 * @author Felix Kaasa
 */

public class Square<T> {
    private static int numberOfSquares = 0;
    private final int Id;
    private T piece;

    public Square(){
        this.Id = numberOfSquares;
        numberOfSquares++;
    }

    public Square(int id){
        this.Id = id;
        numberOfSquares++;
    }

    int getSquareId(){return this.Id;}

    void setPiece(T piece){this.piece = piece;}

    public T getPiece(){
        if (this.isEmpty()){ return null;}
        return this.piece;
    }

    public boolean isEmpty(){
        if (this.piece == null){ return true;}
        return false;
    }

    public void removePiece(){this.piece = null;}
}
