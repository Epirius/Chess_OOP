/**
 * @author Felix Kaasa
 */

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class DiagonalMoves {

    /**
     * @param Id is the id number of a square (0-63)
     * @param board object that holds all squares
     * @return a list of Moves that start from ID, and ends in all diagonal squares.
     */
    public static List<Move> getDiagonalMoves(int Id, Board board){
        List<Integer> diagonalSquareId = findDiagonalSquares(Id, board);
        int numberOfMoves = diagonalSquareId.size();
        List<Move> moves = new ArrayList<Move>();

        for (int i = 0; i < numberOfMoves; i++) {
            int to = diagonalSquareId.get(i);
            moves.add(new Move(Id, to));
        }

        return moves;
    }

    /**
     * checks in each direction from Id. if the target is in the same line as Id, it returns a list of square id's from (exclusive) id to the edge of the board in that line.
     * if target is not in any direction, it returns an empty list.
     * @param Id is the square we search from
     * @param target is the square we are searching for
     * @return list of integers
     */
    public static List<Integer> getDiagonalLine(int Id, int target){
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        Board board = new Board(false);
        Pawn pawn = new Pawn(Team.WHITE);
        board.getSquare(Id).setPiece(pawn);

        List<Integer> directionDownRight = checkDirection(7 - x, y, -7, Id, board);
        if (directionDownRight.contains(target)){return directionDownRight;}
        List<Integer> directionDownLeft = checkDirection(x, y, -9, Id, board);
        if (directionDownLeft.contains(target)){return directionDownLeft;}
        List<Integer> directionUpLeft = checkDirection(x, 7 - y, 7, Id, board);
        if (directionUpLeft.contains(target)){return directionUpLeft;}
        List<Integer> directionUpRight = checkDirection(7 - x, 7 - y, 9, Id, board);
        if (directionUpRight.contains(target)){return directionUpRight;}
        return new ArrayList<Integer>();
    }

    /**
     * @param Id is the id number of a square (0-63)
     * @param board object that holds all squares
     * @return a list of integers corresponding to diagonal squares.
     */
    private static List<Integer> findDiagonalSquares(int Id, Board board){
        List<Integer> diagonalSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        
        int lenFromRightEdge = 7 - x;
        int lenFromLeftEdge = x;
        int lenFromTop = 7 - y;
        int lenFromBottom = y;

        // down and to the right
        diagonalSquareId.addAll(checkDirection(lenFromRightEdge, lenFromBottom, -7, Id, board));
        // down and to the left
        diagonalSquareId.addAll(checkDirection(lenFromLeftEdge, lenFromBottom, -9, Id, board));
        // up and to the left
        diagonalSquareId.addAll(checkDirection(lenFromLeftEdge, lenFromTop, 7, Id, board));
        // up and to the right
        diagonalSquareId.addAll(checkDirection(lenFromRightEdge, lenFromTop, 9, Id, board));
        return diagonalSquareId;
    }

    private static List<Integer> checkDirection(int lenFromHorizontalEdge, int lenFromVerticalEdge, int step, int Id, Board board) {
        List<Integer> output = new ArrayList<>();
        Team team = board.getSquare(Id).getPiece().team;
        for (int i = 1; i < min(lenFromHorizontalEdge, lenFromVerticalEdge) + 1; i++) {
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).isEmpty()) {
                output.add(currentSquare);
                continue;
            }
            else if (board.getSquare(currentSquare).getPiece().team != team) {
                output.add(currentSquare);
                break;
            }
            break;
        }
        return output;
    }

    // NB: this is used for testing only, should not be used in the normal program.
    public static List<Integer> testingDiagonal(int Id){
        Board board = new Board(false);
        board.getSquare(Id).setPiece(new Queen(Team.WHITE));
        return findDiagonalSquares(Id, board);
    }
}