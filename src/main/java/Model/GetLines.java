package Model;

import Model.Pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class GetLines {

    /**
     * This method gives all possible moves a piece could take in a diagonal or straight line.
     * @param Id the int position of the piece.
     * @param board the board the piece is moving on
     * @param diagonal true if you want diagonal lines
     * @param straight true if you want straight lines
     * @return
     */
    public static List<Move> getMoves(int Id, Board board, boolean diagonal, boolean straight) {
        if (!diagonal && !straight) {
            throw new RuntimeException("getMoves checks in at least either a straight or diagonal line. you gave false value to both directions.");
        }

        List<Integer> squareId = findSquares(Id, board, diagonal, straight);

        int numberOfMoves = squareId.size();
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < numberOfMoves; i++) {
            int to = squareId.get(i);
            moves.add(new Move(Id, to));
        }
        return moves;
    }

    /**
     * this method returns a list of integers for all squares in a row from id to edge of board, if there exists a row between the id and target.
     * @param id the int id of a square
     * @param target the int id of the target square
     * @return returns a list of integers if there exist a line, else an empty list.
     */
    public static List<Integer> getLine(int id, int target){
        int x = IBoard.squareToCoordinates(id)[0];
        int y = IBoard.squareToCoordinates(id)[1];
        Board board = new Board(false);
        Pawn pawn = new Pawn(Team.WHITE);
        board.getSquare(id).setPiece(pawn);

        List<Integer> directionDown = checkDirection(y, Direction.SOUTH.direction, id, board);
        if (directionDown.contains(target)){return directionDown;}
        List<Integer> directionLeft =checkDirection(x, Direction.WEST.direction, id, board);
        if (directionLeft.contains(target)){return directionLeft;}
        List<Integer> directionRight = checkDirection(7 - x, Direction.EAST.direction, id, board);
        if (directionRight.contains(target)){return directionRight;}
        List<Integer> directionUp =checkDirection(7 - y, Direction.NORTH.direction, id, board);
        if (directionUp.contains(target)){return directionUp;}
        List<Integer> directionDownRight = checkDirection(7 - x, y, Direction.SOUTHEAST.direction, id, board);
        if (directionDownRight.contains(target)){return directionDownRight;}
        List<Integer> directionDownLeft = checkDirection(x, y, Direction.SOUTHWEST.direction, id, board);
        if (directionDownLeft.contains(target)){return directionDownLeft;}
        List<Integer> directionUpLeft = checkDirection(x, 7 - y, Direction.NORTHWEST.direction, id, board);
        if (directionUpLeft.contains(target)){return directionUpLeft;}
        List<Integer> directionUpRight = checkDirection(7 - x, 7 - y, Direction.NORTHEAST.direction, id, board);
        if (directionUpRight.contains(target)){return directionUpRight;}
        return new ArrayList<Integer>();
    }

    /**
     * helper function for getMoves. it calls checkDirection in the directions getMoves wants.
     */
    private static List<Integer> findSquares(int Id, Board board, boolean diagonal, boolean straight){
        List<Integer> squareIdList = new ArrayList<Integer>();
        int x = IBoard.squareToCoordinates(Id)[0];
        int y = IBoard.squareToCoordinates(Id)[1];

        int lenFromRightEdge = 7 - x;
        int lenFromLeftEdge = x;
        int lenFromTop = 7 - y;
        int lenFromBottom = y;


        if (straight) {
            // down
            squareIdList.addAll(checkDirection(lenFromBottom, -8, Id, board));
            // left
            squareIdList.addAll(checkDirection(lenFromLeftEdge, -1, Id, board));
            // right
            squareIdList.addAll(checkDirection(lenFromRightEdge, 1, Id, board));
            // up
            squareIdList.addAll(checkDirection(lenFromTop, 8, Id, board));
        }

        if (diagonal) {
            // down and to the right
            squareIdList.addAll(checkDirection(lenFromRightEdge, lenFromBottom, -7, Id, board));
            // down and to the left
            squareIdList.addAll(checkDirection(lenFromLeftEdge, lenFromBottom, -9, Id, board));
            // up and to the left
            squareIdList.addAll(checkDirection(lenFromLeftEdge, lenFromTop, 7, Id, board));
            // up and to the right
            squareIdList.addAll(checkDirection(lenFromRightEdge, lenFromTop, 9, Id, board));
        }
        return squareIdList;
    }


    /**
     * checks the squares in a direction. if the square has an enemy, that square will be added, but no further squares will be checked.
     * if the square is a friend, it will not be added and no further squares will be checked.
     * @param lenFromHorizontalEdge int len from edge
     * @param lenFromVerticalEdge int len from edge
     * @param step the int value of a direction
     * @param Id start square
     * @param board board
     * @return list of integers
     */
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

    /**
     * checks the squares in a direction. if the square has an enemy, that square will be added, but no further squares will be checked.
     * if the square is a friend, it will not be added and no further squares will be checked.
     * @param lenFromEdge int len from edge
     * @param step the int value of a direction
     * @param Id start square
     * @param board board
     * @return list of integers
     */
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
}
