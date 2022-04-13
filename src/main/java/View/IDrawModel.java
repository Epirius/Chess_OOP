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

    void upgradePawn(Type type);

    Team getTeam();

    Move getLastMove();

    Piece getPiece(int id);

    int getScore();

    void undoMove();

    void undoMove(int numMoves);
}
