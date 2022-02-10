public interface IBoard {

    // create a 8x8 board of squares.
    void createBoard();


    // place all the pieces for both teams in their starting positions.
    void initBoard();

    Square getSquare(int Id);

    // takes in a square id, and returns the piece that is on that square.
    Piece getPiece(int squareId);

}
