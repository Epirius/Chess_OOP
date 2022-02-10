public interface IBoard {

    // create a 8x8 board of squares.
    void createBoard();


    // place all the pieces for both teams in their starting positions.
    void initBoard();

    Square getSquare(int Id);

    // takes in a square id, and returns the piece that is on that square.
    Piece getPiece(int squareId);

    // takes in the id of a square and returns a int list with x,y coordinates (from 0,0 to 7,7)
    int[] squareToCoordinates(int Id) throws Exception;

    // takes in a in list with x,y coordinates (from 0,0 to 7,7) and return the id of the square.
    int coordinatesToSquare(int[] coords);

}
