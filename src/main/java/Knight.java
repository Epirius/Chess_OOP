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
    public List<Move> getPossibleMoves(int position, Board board) {
        List<Move> moves = new ArrayList<>();
        int y = IBoard.squareToCoordinates(position)[1];
        int[] steps = new int[]{-17, -15, -10, -6, 6, 10, 15, 17}; // all the directions a Knight can move

        for (int step : steps){
            if (position + step > 63 || position + step < 0){continue;}
            if (!board.getSquare(position + step).isEmpty() &&
                    board.getSquare(position + step).getPiece().team == team){continue;} // friendly piece blocking

            //checking if out of bounds
            int newY = IBoard.squareToCoordinates(position + step)[1];
            if (step == -17 && newY != y - 2){continue;}
            if (step == -15 && newY != y - 2){continue;}
            if (step == -10 && newY != y - 1){continue;}
            if (step == -6 && newY != y - 1){continue;}
            if (step == 6 && newY != y + 1){continue;}
            if (step == 10 && newY != y + 1){continue;}
            if (step == 15 && newY != y + 2){continue;}
            if (step == 17 && newY != y + 2){continue;}

            moves.add(new Move(position, position + step));
        }
        return moves;
    }
}
