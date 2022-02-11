import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */

public class Bishop extends Piece{

    public Bishop(Team team) {
        super(team, Type.BISHOP);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {
        return new ArrayList<Move>();
    }
}
