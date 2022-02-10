/**
 * @author Felix Kaasa
 */

public interface IPiece <PieceType> {

    // returns what team a piece is on
    Team getTeam();

    // returns the type of chess piece it is
    Type getPiece();

    // This returns all moves for a piece if it was the only piece on the board.
    Move[] getPossibleMoves();

    /*
    ** Returns a list of all legal moves TODO: change return type to list of moves
    ** @params input of all possible moves for a piece on an empty board.
    */
    Move[] getLegalMoves();

    // Move the piece from current square to another
    void move();



}
