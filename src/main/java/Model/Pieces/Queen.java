package Model.Pieces;

import Model.*;

import java.util.*;

/**
 * @author Felix Kaasa
 */

public class Queen extends Piece{

    public Queen(Team team) {
        super(team, Type.QUEEN);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board){
        List<Move> output = getLines.getMoves(position, board, true, true);
        return output;
    }
}
