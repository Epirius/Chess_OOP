package Model;

import Model.Pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Felix Kaasa
 */
public class MoveHistory {
    public static int numberOfMoves = 0;
    public final int moveID;
    public final Team currentPlayer;
    public final List<Piece> whitePieces;
    public final List<Piece> blackPieces;
    public final List<Piece> deadPieces;
    public final Move move;
    public final Clock clock;

    /**
     * constructor for move history
     * @param board a reference to the board that is to be copied.
     * @param move the move that was made at this state. (NB: not the one made to get to this state.)
     */
    public MoveHistory(Board board, Move move, Clock clock) {
        numberOfMoves++;
        this.moveID = numberOfMoves;
        this.currentPlayer = board.getTeam();
        this.move = move;
        this.whitePieces = copyPieceList(board.whitePieces);
        this.blackPieces = copyPieceList(board.blackPieces);
        this.deadPieces = copyPieceList(board.deadPieces);

        if (clock != null) {
            this.clock = clock.clone();
            this.clock.enabled = false;
        } else {
            this.clock = null;
        }
    }

    /**
     * a method to clone all the pieces in a list to a new list
     * @param originalList the original list that should be cloned
     * @return a list of cloned pieces
     */
    private List<Piece> copyPieceList(List<Piece> originalList){
        List<Piece> copyList = new ArrayList<>();
        for (Piece piece : originalList){
            copyList.add(piece.clone());
        }
        return copyList;
    }
}
