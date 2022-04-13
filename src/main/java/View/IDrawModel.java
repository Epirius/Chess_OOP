package View;

import Model.Move;
import Model.Pieces.Piece;
import Model.Team;
import Model.Type;

import java.util.List;

/**
 * @author Felix Kaasa
 */
public interface IDrawModel {

    /**
     * get all the pieces on the board
     * @return a list of ViewPiece containing all pieces on the board.
     */
    List<ViewPiece> getPiecesOnTheBoard();

    /**
     * get all pieces that have been killed
     * @return list of dead pieces
     */
    List<ViewPiece> getDeadViewPieces(Team team);

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

    /**
     * a method to get the last move from the moveHistoryList
     * @return Move
     */
    Move getLastMove();

    /**
     * get piece at square
     * @param id of the square that you want the piece from
     * @return a Piece from the given square
     */
    Piece getPiece(int id);

    /**
     * gets the current score of the game, positive if white is winning, negative if black is winning.
     * @return score of the current game
     */
    int getScore();

    /**
     * method to undo the last move.
     */
    void undoMove();

    /**
     * a method to undo multiple moves
     * @param numMoves number of moves to undo
     */
    void undoMove(int numMoves);
}
