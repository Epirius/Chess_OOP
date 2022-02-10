import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class DiagonalMoves {

    /**
     * @param Id is the id number of a square (0-63)
     * @param board object that holds all squares
     * @return a list of Moves that start from ID, and ends in all diagonal squares.
     */
    public static Move[] getDiagonalMoves(int Id, Board board) throws Exception {
        List<Integer> diagonalSquareId = findDiagonalSquares(Id, board);
        int numberOfMoves = diagonalSquareId.size();
        Move[] moves = new Move[numberOfMoves];

        for (int i = 0; i < numberOfMoves; i++) {
            int to = diagonalSquareId.get(i);
            moves[i] = new Move(Id, to);
        }

        return moves;
    }

    /**
     * @param Id is the id number of a square (0-63)
     * @param board object that holds all squares
     * @return a list of integers corresponding to diagonal squares.
     */
    private static List<Integer> findDiagonalSquares(int Id, Board board) throws Exception {
        List<Integer> diagonalSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        Team team = board.getSquare(Id).getPiece().team;
        
        int lenFromRightEdge = 7 - x;
        int lenFromLeftEdge = x;
        int lenFromTop = 7 - y;
        int lenFromBottom = y;

        // down and to the right
        for (int i = 1; i < min(lenFromRightEdge, lenFromBottom) + 1; i++) {
            int step = -7;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).getPiece() == null) {
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team) {
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        // down and to the left
        for (int i = 1; i < min(lenFromLeftEdge, lenFromBottom) + 1; i++) {
            int step = -9;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).getPiece() == null) {
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team) {
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        // up and to the left
        for (int i = 1; i < min(lenFromLeftEdge, lenFromTop) + 1; i++) {
            int step = 7;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).getPiece() == null) {
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team) {
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        // up and to the right
        for (int i = 1; i < min(lenFromRightEdge, lenFromTop) + 1; i++) {
            int step = 9;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).getPiece() == null) {
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team) {
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        return diagonalSquareId;
    }

    // NB: this is used for testing only, should not be used in the normal program.
    public static List<Integer> testingDiagonal(int Id) throws Exception {
        Board board = new Board();
        board.getSquare(Id).setPiece(new Queen(Team.WHITE));
        return findDiagonalSquares(Id, board);
    }
}