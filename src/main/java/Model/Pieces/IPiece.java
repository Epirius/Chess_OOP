package Model.Pieces;

import Model.*;

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
}
