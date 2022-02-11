import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */

public class Knight extends Piece{

    public Knight(Team team) {
        super(team, Type.KNIGHT);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board)  {
        return new ArrayList<Move>();
    }
}
