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
        return null;
    }

    /**
     * converts coordinates to id
     * @param coordinates from 0,0 to 7,7
     * @return int from 0 to 63
     */
    static int coordinatesToSquare(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        int id = x + (y * 8);
        return id;
    }

}
