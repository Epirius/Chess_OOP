package Model;

import Model.Pieces.Piece;

import java.util.List;

/**
 * @author Felix Kaasa
 */

public interface IBoard {

    /**
     * creates an 8x8 board of squares
     */
    void createBoard();

    /**
     * places all the pieces for each team in their starting positions
     */
    void initBoard();

    /**
     * gets a square
     * @param Id id of the square
     * @return the square with the id
     */
    Square<Piece> getSquare(int Id);

    /**
     * takes in a square id, and returns the piece that is on that square.
     * @param squareId id of the square the piece is standing on
     * @return the piece on the given square
     */
    Piece getPiece(int squareId);

    /**
     * moves a piece from one square to another.
     * @param move takes in a LEGAL! move.
     */
    public void doMove(Move move);


    /**
     * converts id to coordinates
     * @param Id int from 0 to 63
     * @return coordinates from 0,0 to 7,7
     */
    static int[] squareToCoordinates(int Id){
        int x;
        int y;

        for (int i = 0; i < 8; i++) {
            int calculation = Id - (8 * i);
            if (calculation >= 0 && calculation < 8){
                x = calculation;
                y = i;
                return  new int[]{x, y};
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * converts coordinates to id
     * @param coordinates from 0,0 to 7,7
     * @return int from 0 to 63
     */
    static int coordinatesToSquare(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        return x + (y * 8);
    }

}
