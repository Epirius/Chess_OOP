package Model;

import Model.Pieces.Piece;

/**
 * @author Felix Kaasa
 */

public class Square<T> {
    private static final int maxSquares = 63;
    private static int numberOfSquares = 0;
    private final int Id;
    private T piece;

    public Square(){
        this.Id = numberOfSquares;
        numberOfSquares++;
        if (this.Id > maxSquares) {
            throw new RuntimeException("Too many squares were created!");
        }
    }

    /*
    public Square(int id){
        this.Id = id;
        numberOfSquares++;
    }

     */

    /**
     * get the id number of the square
     * @return int id
     */
    int getSquareId(){return this.Id;}

    /**
     * set the content of the square
     * @param piece the piece that is to be set for this square
     */
    void setPiece(T piece){this.piece = piece;}

    /**
     * method to get the contents of the square
     * @return the content of the square
     */
    public T getPiece(){
        if (this.isEmpty()){ return null;}
        return this.piece;
    }

    /**
     * checks if the square is empty
     * @return true if it is empty
     */
    public boolean isEmpty(){
        if (this.piece == null){ return true;}
        return false;
    }

    /**
     * sets the piece variable to null
     */
    public void removePiece(){this.piece = null;}

    /**
     * a method that resets the static variable that tracks the number of squares that has been created,
     * this method needs to be called when a new board is created, so that the squares that are created start with an id of 0.
     */
    public static void resetNumberOfSquares(){
        numberOfSquares = 0;
    }
}
