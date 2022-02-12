import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */
public class PlussMoves {
    public static List<Move> getPlussMoves(int Id, Board board){
        List<Integer> plussSquareId = findPlussSquares(Id, board);
        int numberOfMoves = plussSquareId.size();
        List<Move> moves = new ArrayList<Move>();

        for (int i = 0; i < numberOfMoves; i++) {
            int to = plussSquareId.get(i);
            moves.add(new Move(Id, to));
        }

        return moves;
    }

    private static List<Integer> findPlussSquares(int Id, Board board){
        List<Integer> plussSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        Team team = board.getSquare(Id).getPiece().team;

        int lenFromRightEdge = 7 - x;
        int lenFromLeftEdge = x;
        int lenFromTop = 7 - y;
        int lenFromBottom = y;

        // down
        for (int i = 1; i < lenFromBottom + 1; i++) {
            int step = -8;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).isEmpty()) {
                plussSquareId.add(currentSquare);
                continue;
            }
            else if (board.getSquare(currentSquare).getPiece().team != team) {
                plussSquareId.add(currentSquare);
                break;
            }
            break;
        }

        // left
        for (int i = 1; i < lenFromLeftEdge + 1; i++) {
            int step = -1;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).isEmpty()) {
                plussSquareId.add(currentSquare);
                continue;
            }
            else if (board.getSquare(currentSquare).getPiece().team != team) {
                plussSquareId.add(currentSquare);
                break;
            }
            break;
        }

        // right
        for (int i = 1; i < lenFromRightEdge + 1; i++) {
            int step = 1;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).isEmpty()) {
                plussSquareId.add(currentSquare);
                continue;
            }
            else if (board.getSquare(currentSquare).getPiece().team != team) {
                plussSquareId.add(currentSquare);
                break;
            }
            break;
        }

        // up
        for (int i = 1; i < lenFromTop + 1; i++) {
            int step = 8;
            int currentSquare = Id + (step * i);
            if (board.getSquare(currentSquare).isEmpty()) {
                plussSquareId.add(currentSquare);
                continue;
            }
            else if (board.getSquare(currentSquare).getPiece().team != team) {
                plussSquareId.add(currentSquare);
                break;
            }
            break;
        }
        return plussSquareId;
    }

    // NB: this is used for testing only, should not be used in the normal program.
    public static List<Integer> testingPluss(int Id){
        Board board = new Board(false);
        board.getSquare(Id).setPiece(new Queen(Team.WHITE));
        return findPlussSquares(Id, board);
    }
}
