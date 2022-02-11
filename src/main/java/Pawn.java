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
        List<Move> moves = new ArrayList<>();
        int startRow = ((team == Team.WHITE) ? 1 : 6);
        int directionOfStep = ((team == Team.WHITE) ? 8 : -8);
        int y = IBoard.squareToCoordinates(position)[1]; // coordinate y


        for (int step : new int[]{directionOfStep - 1, directionOfStep + 1}){
            if (position + step > 63 || position + step < 0){continue;}
            if (board.getSquare(position + step).getPiece() == null ||
                    board.getSquare(position + step).getPiece().team == team){continue;} // friendly piece blocking

            // checking if out of bounds
            int newY = IBoard.squareToCoordinates(position + step)[1];
            if (team == Team.WHITE && y + 1 != newY){continue;}
            if (team == Team.BLACK && y - 1 != newY){continue;}
            moves.add(new Move(position, position + step));
        }
        if (board.getSquare(position + directionOfStep).getPiece() == null){
            moves.add(new Move(position, position + directionOfStep));
        }
        if (board.getSquare(position + directionOfStep * 2).getPiece() == null){
            if (team == Team.WHITE && y == startRow) {
                moves.add(new Move(position, position + directionOfStep * 2));
            }
            if (team == Team.BLACK && y == startRow) {
                moves.add(new Move(position, position + directionOfStep * 2));
            }
        }
        return moves;
    }
}
