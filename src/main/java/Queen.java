import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Felix Kaasa
 */

public class Queen extends Piece{

    public Queen(Team team) {
        super(team, Type.QUEEN);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board){
        List<Move> output = new ArrayList<Move>();
        output.addAll(PlussMoves.getPlussMoves(position, board));
        output.addAll(DiagonalMoves.getDiagonalMoves(position, board));
        return output;
    }
}
