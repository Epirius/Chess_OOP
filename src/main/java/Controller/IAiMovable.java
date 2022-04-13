package Controller;

import Model.Move;
import Model.Pieces.Piece;
import Model.Team;
import Model.Type;

import java.util.List;

/**
 * @author Felix Kaasa
 */
public interface IAiMovable {

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
     * method to undo the last move.
     */
    void undoMove();

    /**
     * finds all legal move for the current player.
     * @return List<Move>
     */
    List<Move> getLegalMoves();

    /**
     * gets the current score of the game, positive if white is winning, negative if black is winning.
     * @return score of the current game
     */
    int getScore();

    /**
     * gets the move id of the last turn
     * @return int number of turns that have been made since the start.
     */
    int getCurrentTurn();

    /**
     * method to upgrade a pawn when it gets to the last row
     * @param type QUEEN, ROOK, BISHOP or KNIGHT
     */
    void upgradePawn(Type type);

    /**
     * gets the team of the current player
     * @return Team.WHITE or Team.BLACK.
     */
    Team getTeam();
}
