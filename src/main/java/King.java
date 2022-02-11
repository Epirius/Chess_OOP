import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */

public class King extends Piece{

    public King(Team team) {
        super(team, Type.KING);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {
        List<Move> moves = new ArrayList<>();
        int[] steps = new int[]{-7, -8, -9, -1, 1, 7, 8, 9};
        for (int step : steps){

        }
    }
}
