package Model.Pieces;

import Model.*;

import java.util.List;

/**
 * @author Felix Kaasa
 */

public class Bishop extends Piece{
    public static final int value = 3;

    public Bishop(Team team) {
        super(team, Type.BISHOP, value);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {
        return GetLines.getMoves(position, board, true, false);
    }
}
