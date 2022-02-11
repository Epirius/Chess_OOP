import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */

public class Pawn extends Piece{


    public Pawn(Team team) {
        super(team, Type.PAWN);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {
        return new ArrayList<Move>();
    }
}
