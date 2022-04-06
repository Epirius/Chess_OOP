package Model.Pieces;

import Model.*;

import java.util.List;

/**
 * @author Felix Kaasa
 */

public class Rook extends Piece{
    public static final int value = 5;

    public Rook(Team team) {
        super(team, Type.ROOK, value);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {
        return GetLines.getMoves(position, board, false, true);
    }

}
