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

    /**
     * checks in each direction from Id. if the target is in the same line as Id, it returns a list of square id's from id to the edge of the board in that line.
     * if target is not in any direction, it returns an empty list.
     * @param Id is the square we search from
     * @param target is the square we are searching for
     * @return list of integers
     */
    public static List<Integer> getPlussSquareLine(int Id, int target){
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        Board board = new Board(false);
        Pawn pawn = new Pawn(Team.WHITE);
        board.getSquare(Id).setPiece(pawn);

        List<Integer> directionDown = checkDirection(y, -8, Id, board);
        if (directionDown.contains(target)){return directionDown;};
        List<Integer> directionLeft =checkDirection(x, -1, Id, board);
        if (directionLeft.contains(target)){return directionLeft;};
        List<Integer> directionRight = checkDirection(7 - x, 1, Id, board);
        if (directionRight.contains(target)){return directionRight;};
        List<Integer> directionUp =checkDirection(7 - y, 8, Id, board);
        if (directionUp.contains(target)){return directionUp;};
        return new ArrayList<Integer>();
    }

    private static List<Integer> findPlussSquares(int Id, Board board){
        List<Integer> plussSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];

        int lenFromRightEdge = 7 - x;
        int lenFromLeftEdge = x;
        int lenFromTop = 7 - y;
        int lenFromBottom = y;

        // down
        plussSquareId.addAll(checkDirection(lenFromBottom, -8, Id, board));
        // left
        plussSquareId.addAll(checkDirection(lenFromLeftEdge, -1, Id, board));
        // right
        plussSquareId.addAll(checkDirection(lenFromRightEdge, 1, Id, board));
        // up
        plussSquareId.addAll(checkDirection(lenFromTop, 8, Id, board));
        return plussSquareId;
    }

    private static List<Integer> checkDirection(int lenFromEdge, int step, int Id, Board board) {
        List<Integer> output = new ArrayList<>();
        Team team = board.getSquare(Id).getPiece().team;
        for (int i = 1; i < lenFromEdge + 1; i++) {
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
    public static List<Integer> testingPluss(int Id){
        Board board = new Board(false);
        board.getSquare(Id).setPiece(new Queen(Team.WHITE));
        return findPlussSquares(Id, board);
    }
}
