/**
 * @author Felix Kaasa
 */

public interface IBoard {

    // create a 8x8 board of squares.
    void createBoard();


    // place all the pieces for both teams in their starting positions.
    void initBoard();

    Square getSquare(int Id);

    // takes in a square id, and returns the piece that is on that square.
    Piece getPiece(int squareId);

    // takes in the id of a square and returns an int list with x,y coordinates (from 0,0 to 7,7)
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
        return null;
    }

    // takes in a in list with x,y coordinates (from 0,0 to 7,7) and return the id of the square.
    static int coordinatesToSquare(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        int id = x + (y * 8);
        return id;
    }

}
