package Model;

import Model.Pieces.Piece;

/**
 * @author Felix Kaasa
 */

public class Square {
    private final int Id;
    private Piece piece;

    public Square(int Id){this.Id = Id;}

    int getSquareId(){return this.Id;}

    void setPiece(Piece piece){this.piece = piece;}

    public Piece getPiece(){
        if (this.isEmpty()){ return null;}
        return this.piece;
    }

    public boolean isEmpty(){
        if (this.piece == null){ return true;}
        return false;
    }

    public void removePiece(){this.piece = null;}
}
