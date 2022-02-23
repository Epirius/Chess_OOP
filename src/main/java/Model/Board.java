package Model;

import Model.Pieces.*;
import java.util.*;

/**
 * @author Felix Kaasa
 */


public class Board implements IBoard{

    private final Square[] squares = new Square[64];
    protected List<Piece> whitePieces = new ArrayList<>();
    protected List<Piece> blackPieces = new ArrayList<>();
    public Stack<Move> moveHistory = new Stack<>();
    private boolean currentPlayerIsWhite = true;



    /**
     * The normal constructor for Board
     */
    public Board(){
        createBoard();
        initBoard();
    }

    /**
     * alternative constructor used for testing.
     * @param initialize -if true: same as the normal constructor. if false: creates an empty board.
     */
    public Board(boolean initialize){
        createBoard();
        if (initialize){ initBoard();}
    }

    @Override
    public void createBoard() {
        for (int i = 0; i < 64; i++) {
            squares[i] = new Square(i);
        }
    }

    @Override
    public void initBoard() {

        for (int i = 0; i < 8; i++) {blackPieces.add(new Pawn(Team.BLACK));}

        for (Team team : Team.values()){
            List<Piece> teamList = (Team.WHITE == team ? whitePieces : blackPieces);
            teamList.add(new Rook(team));
            teamList.add(new Knight(team));
            teamList.add(new Bishop(team));
            teamList.add(new Queen(team));
            teamList.add(new King(team));
            teamList.add(new Bishop(team));
            teamList.add(new Knight(team));
            teamList.add(new Rook(team));
        }
        for (int i = 0; i < 8; i++) {whitePieces.add(new Pawn(Team.WHITE));}

        for (int i = 0; i < 16; i++) {
            getSquare(i).setPiece(whitePieces.get(i));
            whitePieces.get(i).setPosition(i);
            getSquare(i + 48).setPiece(blackPieces.get(i));
            blackPieces.get(i).setPosition(i + 48);
        }
    }

    @Override
    public Square getSquare(int Id){
        return squares[Id];
    }

    @Override
    public Piece getPiece(int squareId) {
        return getSquare(squareId).getPiece();
    }

    @Override
    public void doMove(Move move) {
        // TODO move to Model.Model maybe??
        int from = move.getMove()[0];
        int to = move.getMove()[1];
        Piece movingPiece = getSquare(from).getPiece();

        if (!getSquare(to).isEmpty()){
            kill(to);
        }
        if (move.enPassant){kill(move.enPassantPosition);}
        getSquare(to).setPiece(movingPiece);
        getSquare(from).removePiece();
        movingPiece.setPosition(to);
        moveHistory.add(move);
        currentPlayerIsWhite = !currentPlayerIsWhite;
    }

    /**
     * handles the removal of a captured piece
     * @param Id id of the square with the captured piece
     */
    private void kill(int Id){
        // TODO move to Model.Model maybe ??
        // TODO do something with points here
        Piece deathRowPiece = getSquare(Id).getPiece();
        List<Piece> teamList = (Team.WHITE == deathRowPiece.team ? whitePieces : blackPieces);
        teamList.remove(deathRowPiece);
        getSquare(Id).removePiece();
    }

    public boolean isCurrentPlayerIsWhite(){
        return currentPlayerIsWhite;
    }

    /**
     * checks if a square is occupied by a friendly piece.
     * @param squareId Id of the square with the piece to be checked.
     * @return returns true if piece is friendly.
     */
    public boolean isSquareFriendly(int squareId){
        if (isCurrentPlayerIsWhite() && getPiece(squareId).team == Team.WHITE){return true;}
        else if (!isCurrentPlayerIsWhite() && getPiece(squareId).team == Team.BLACK){return true;}
        return false;
    }

    /**
     * used to get the squares between two squares.
     * @param squareId
     * @param target
     * @return a list of squares between two square Id's, returns empty if they are not on the same line.
     */
    protected List<Square> squaresBetween(int squareId, int target){
        List<Square> squares = new ArrayList<>();
        List<Integer> line = GetLines.getLine(squareId, target);
        if (line.size() == 0){return new ArrayList<Square>();}

        int targetIndex = line.indexOf(target);
        for (int i = 0; i < targetIndex; i++) {
            squares.add(this.getSquare(line.get(i)));
        }
        return squares;
    }
}





































