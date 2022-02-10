import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class DiagonalMoves {

    // takes in a square, returns all diagonal squares in the form of a Move list
    public static Move[] getDiagonalMoves(int Id, Board board) throws Exception {
        Team team = board.getPiece(Id).team;
        List<Integer> diagonalSquareId = new ArrayList<Integer>();

        // calling a function in each direction that checks if the next square is empty or is an enemy.
        // it returns a list of empty square and max 1 enemy square.
        List<Integer> upRightMoves = upRight(Id, board, team);
        if (upRightMoves.size() > 0){diagonalSquareId.addAll(upRightMoves);}

        List<Integer> upLeftMoves = upLeft(Id, board, team);
        if (upLeftMoves.size() > 0){diagonalSquareId.addAll(upLeftMoves);}

        List<Integer> downRightMoves = downRight(Id, board, team);
        if (downRightMoves.size() > 0){diagonalSquareId.addAll(downRightMoves);}

        List<Integer> downLeftMoves = downLeft(Id, board, team);
        if (downLeftMoves.size() > 0){diagonalSquareId.addAll(downLeftMoves);}


        // converts the lists i got in the recursive calls to a Move[] array
        // Move[] output = moves.toArray(new Move[moves.size()]); TODO: convert the list of ints into a list of moves and return that

        //return  output;
        return new Move[2]; //TODO delete this

    }
    private static List<Integer> upRight(int Id, Board board, Team team) throws Exception {
        List<Integer> diagonalSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        int lenFromRightEdge;
        int lenFromTop = 7 - y;

        //finds the length from the right edge.
        if (y == 0){lenFromRightEdge = 7 - x;}
        else {lenFromRightEdge = 7 - (x - ((y - 1) * 8));}

        //checking each square along the diagonal, stop if the square is occupied
        for (int i = 0; i < min(lenFromRightEdge, lenFromTop); i++) {
            int currentSquare = Id + (9 * i);
            if (board.getSquare(currentSquare).getPiece() == null){
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team){
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        return diagonalSquareId;
    }

    private static List<Integer> upLeft(int Id, Board board, Team team) throws Exception {
        List<Integer> diagonalSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        int lenFromLeftEdge;
        int lenFromTop = 7 - y;

        //finds the length from the right edge.
        if (y == 0){lenFromLeftEdge = x;}
        else {lenFromLeftEdge = x - ((y - 1) * 8);}

        //checking each square along the diagonal, stop if the square is occupied
        for (int i = 0; i < min(lenFromLeftEdge, lenFromTop); i++) {
            int currentSquare = Id + (7 * i);
            if (board.getSquare(currentSquare).getPiece() == null){
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team){
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        return diagonalSquareId;
    }
    private static List<Integer> downRight(int Id, Board board, Team team) throws Exception {
        List<Integer> diagonalSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        int lenFromRightEdge;
        int lenFromBottom = y;

        //finds the length from the right edge.
        if (y == 0){lenFromRightEdge = 7 - x;}
        else {lenFromRightEdge = 7 - (x - ((y - 1) * 8));}

        //checking each square along the diagonal, stop if the square is occupied
        for (int i = 0; i < min(lenFromRightEdge, lenFromBottom); i++) {
            int currentSquare = Id - (7 * i);
            if (board.getSquare(currentSquare).getPiece() == null){
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team){
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        return diagonalSquareId;
    }
    private static List<Integer> downLeft(int Id, Board board, Team team) throws Exception {
        List<Integer> diagonalSquareId = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];
        int lenFromLeftEdge;
        int lenFromBottom = y;

        //finds the length from the right edge.
        if (y == 0){lenFromLeftEdge = x;}
        else {lenFromLeftEdge = x - ((y - 1) * 8);}

        //checking each square along the diagonal, stop if the square is occupied
        for (int i = 0; i < min(lenFromLeftEdge, lenFromBottom); i++) {
            int currentSquare = Id - (9 * i);
            if (board.getSquare(currentSquare).getPiece() == null){
                diagonalSquareId.add(currentSquare);
                continue;
            }
            if (board.getSquare(currentSquare).getPiece().team != team){
                diagonalSquareId.add(currentSquare);
                break;
            }
            break;
        }
        return diagonalSquareId;
    }
}
