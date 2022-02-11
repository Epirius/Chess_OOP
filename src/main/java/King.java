import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */

public class King extends Piece{
    private boolean castleKingSide = true;
    private boolean castleQueenSide = true;

    public King(Team team) {
        super(team, Type.KING);
    }

    @Override
    public List<Move> getPossibleMoves(int position, Board board) {
        List<Move> moves = new ArrayList<>();
        int y = IBoard.squareToCoordinates(position)[1]; // coordinate y
        int[] steps = new int[]{-7, -8, -9, -1, 1, 7, 8, 9}; // all the directions a king can move

        for (int step : steps){
            if (position + step > 63 || position + step < 0){continue;}
            if (!board.getSquare(position + step).isEmpty() &&
                    board.getSquare(position + step).getPiece().team == team){continue;} // friendly piece blocking

            //checking if out of bounds
            int newY = IBoard.squareToCoordinates(position + step)[1];
            if (step > 1 && (y + 1 != newY || y == 7)){continue;} // moving up
            if (step < -1 && (y - 1 != newY || y == 0)){continue;} // moving down
            if ((step == -1 || step == 1) && y != newY){continue;} // moving sideways

            moves.add(new Move(position, position + step));

        }
        // adding castling
        if (castleKingSide) {moves.add(new Move(position, position + 2, true));}
        if (castleQueenSide){moves.add(new Move(position, position - 2, true));}

        return moves;
    }

    public void setCastleKingSideToFalse(){castleKingSide = false;}
    public void setCastleQueenSideToFalse(){castleQueenSide = false;}
}