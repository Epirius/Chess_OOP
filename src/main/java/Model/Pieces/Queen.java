package Model.Pieces;

import Model.*;

import java.util.*;

/**
 * @author Felix Kaasa
 */

public class Queen extends Piece{
    public static final int value = 9;

    public Queen(Team team) {
        super(team, Type.QUEEN, value);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board){
        List<Move> output = GetLines.getMoves(position, board, true, true);
        return output;
    }
}
