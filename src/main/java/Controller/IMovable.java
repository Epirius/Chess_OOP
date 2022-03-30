package Controller;

import Model.Move;
import Model.Pieces.Piece;

import java.util.List;

public interface IMovable {

    /**
     * @return returns a list of all current pieces on the board
     */
    List<Piece> getAllPieces();

    /**
     * get piece at square
     * @param id
     * @return
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
    public boolean kingInCheck();


    /**
     * finds all legal move for the current player.
     * @return List<Move>
     */
    List<Move> getLegalMoves();
}
