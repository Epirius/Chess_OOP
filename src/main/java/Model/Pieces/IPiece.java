package Model.Pieces;

import Model.Move;
import Model.Team;
import Model.Type;

import java.util.List;

/**
 * @author Felix Kaasa
 */

public interface IPiece {

    // returns what team a piece is on
    Team getTeam();

    // returns the type of chess piece it is
    Type getPiece();

    int getPosition();
    void setPosition(int Id);

    // This returns all moves for a piece as if it was the only piece on the board.
    List<Move> getPossibleMoves(int position, Board board);

    /*
    ** Returns a list of all legal moves TODO: change return type to list of moves
    ** @params input of all possible moves for a piece on an empty board.
    */
    List<Move> getLegalMoves();



}
