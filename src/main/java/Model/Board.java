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

    //TODO add to interface
    public Team getTeam(){
        if (currentPlayerIsWhite){
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    @Override
    public void doMove(Move move) {
        int from = move.getMove()[0];
        int to = move.getMove()[1];
        Piece movingPiece = getSquare(from).getPiece();

        if (move.isEnPassant()){
            kill(move.enPassantPosition);
        }
        else if (move.isMoveCastle()){
            int rookFrom = move.castleRookMove.from;
            int rookTo = move.castleRookMove.to;
            Piece rookPiece = getPiece(rookFrom);
            getSquare(rookTo).setPiece(rookPiece);
            getSquare(rookFrom).removePiece();
            rookPiece.setPosition(rookTo);
        }
        else if (!getSquare(to).isEmpty()){
            kill(to);
        }
        getSquare(to).setPiece(movingPiece);
        getSquare(from).removePiece();
        movingPiece.setPosition(to);
        moveHistory.add(move);
        currentPlayerIsWhite = !currentPlayerIsWhite;
    }

    //@Override TODO make interface for upgrading
    public void upgradePawn(Type type){
        int id = moveHistory.peek().to;
        if (this.getPiece(id).type != Type.PAWN){
            throw new RuntimeException("the last move was not a pawn, yet upgrade pawn is called.");
        }
        if (id > 7 && id < 56 || id < 0 || id > 63){
            throw new RuntimeException("the position of the pawn to be upgraded is not in the first or last line of the board.");
        }

        Team team = this.getPiece(id).team;
        List<Piece> teamList = (Team.WHITE == team ? whitePieces : blackPieces);
        Piece upgradedPiece;

        switch (type) {
            case QUEEN -> upgradedPiece = new Queen(team);
            case ROOK -> upgradedPiece = new Rook(team);
            case BISHOP -> upgradedPiece = new Bishop(team);
            case KNIGHT -> upgradedPiece = new Knight(team);
            default -> upgradedPiece = new Queen(team);
        }

        teamList.remove(this.getPiece(id));
        getSquare(id).removePiece();
        getSquare(id).setPiece(upgradedPiece);
        upgradedPiece.setPosition(id);
        teamList.add(upgradedPiece);

    }

    /**
     * handles the removal of a captured piece
     * @param Id id of the square with the captured piece
     */
    private void kill(int Id){
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
        if (getSquare(squareId).isEmpty()){return false;}
        if (isCurrentPlayerIsWhite() && getPiece(squareId).team == Team.WHITE){return true;}
        else if (!isCurrentPlayerIsWhite() && getPiece(squareId).team == Team.BLACK){return true;}
        return false;
    }

    /**
     * used to get the squares between two squares.
     * @param squareId id of the square of the first piece
     * @param target id of the square of the second piece
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