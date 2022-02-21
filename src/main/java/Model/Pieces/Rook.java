package Model.Pieces;

import Model.*;

import java.util.List;

/**
 * @author Felix Kaasa
 */

public class Rook extends Piece{

    public Rook(Team team) {
        super(team, Type.ROOK);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {return PlussMoves.getPlussMoves(position, board);}

}
