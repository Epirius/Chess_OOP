import java.util.*;
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
        Set<Move> setWithoutDuplicates = new LinkedHashSet<>(PlussMoves.getPlussMoves(position, board));
        setWithoutDuplicates.addAll(DiagonalMoves.getDiagonalMoves(position, board));
        List<Move> output = new ArrayList<>(setWithoutDuplicates);
        return output;
    }
}
