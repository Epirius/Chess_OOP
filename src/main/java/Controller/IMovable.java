package Controller;

import Model.Move;
import Model.Pieces.Piece;
import Model.Team;
import Model.Type;

import java.util.List;

public interface IMovable {

    /**
     * @return returns a list of all current pieces on the board
     */
    List<Piece> getAllPieces();

    /**
     * get piece at square
     * @param id of the square that you want the piece from
     * @return a Piece from the given square
     */
    Piece getPiece(int id);

    /**
     * a method to send moves to.
     * @param move move to be made.
     */
    void doMove(Move move);

    /**
     * checks if king is in check
     * @return true if king is in check, else false.
     */
    boolean kingInCheck();

    /**
     * finds all legal move for the current player.
     * @return List<Move>
     */
    List<Move> getLegalMoves();

}
