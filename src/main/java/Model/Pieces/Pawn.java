package Model.Pieces;

import Model.*;

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

        // one step
        if ((position + directionOfStep) < 64 && (position + directionOfStep) >= 0 && board.getSquare(position + directionOfStep).isEmpty()){
            moves.add(new Move(position, position + directionOfStep));
        }
        // two steps as the first move
        if ((position + directionOfStep * 2) < 64 && (position + directionOfStep * 2) >= 0 && board.getSquare(position + directionOfStep * 2).isEmpty()){
            if (team == Team.WHITE && y == startRow) {
                moves.add(new Move(position, position + directionOfStep * 2));
            }
            if (team == Team.BLACK && y == startRow) {
                moves.add(new Move(position, position + directionOfStep * 2));
            }
        }

        // En passant
        if (board.moveHistory.size() > 0) {
            Move lastMove = board.moveHistory.peek();
            int lastMoveFrom = lastMove.getMove()[0];
            int lastMoveFromY = IBoard.squareToCoordinates(lastMoveFrom)[1]; // y coordinate.
            int lastMoveTo = lastMove.getMove()[1];
            Piece lastMovePiece = board.getSquare(lastMoveTo).getPiece();
            if (team == Team.WHITE && lastMovePiece.team == Team.BLACK && lastMovePiece.type == Type.PAWN && y == 4) {
                if (position + 17 == lastMoveFrom && position + 1 == lastMoveTo && lastMoveFromY == y + 2) {
                    moves.add(new Move(position, position + 9, position + 1));
                }
                if (position + 15 == lastMoveFrom && position - 1 == lastMoveTo && lastMoveFromY == y + 2) {
                    moves.add(new Move(position, position + 7, position - 1));
                }
            }
            if (team == Team.BLACK && lastMovePiece.team == Team.WHITE && lastMovePiece.type == Type.PAWN && y == 3) {
                if (position - 17 == lastMoveFrom && position - 1 == lastMoveTo && lastMoveFromY == y - 2) {
                    moves.add(new Move(position, position - 9, position - 1));
                }
                if (position - 15 == lastMoveFrom && position + 1 == lastMoveTo && lastMoveFromY == y - 2) {
                    moves.add(new Move(position, position - 7, position + 1));
                }
            }
        }
        return moves;
    }

    // since the pawn attack is not the same as the pawn move, i need another method to get attack squares for the pawn.
    public List<Move> getPossibleThreats(int position, Board board){
        List<Move> output = new ArrayList<>();
        Team team = this.getTeam();

        int directionOfStep = ((team == Team.WHITE) ? 8 : -8);
        int y = IBoard.squareToCoordinates(position)[1]; // coordinate y


        for (int step : new int[]{directionOfStep - 1, directionOfStep + 1}){
            if (position + step > 63 || position + step < 0){continue;}
            if (board.getSquare(position + step).getPiece() != null &&
                    board.getSquare(position + step).getPiece().team == team){continue;} // friendly piece blocking

            // checking if out of bounds
            int newY = IBoard.squareToCoordinates(position + step)[1];
            if (team == Team.WHITE && y + 1 != newY){continue;}
            if (team == Team.BLACK && y - 1 != newY){continue;}

            output.add(new Move(position, position + step));
        }
        return output;
    }
}
