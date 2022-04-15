package Model.Pieces;

import Model.Board;
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

    /**
     * this method is used to get all moves a piece can do if it was alone on the board
     * @param position position of the piece to be checked.
     * @param board a copy of the board.
     * @return list of moves the piece could take (if alone on the board?
     */
    List<Move> getPossibleMoves(int position, Board board);
}
