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
    protected List<Piece> deadPieces = new ArrayList<>();
    public Stack<MoveHistory> moveHistoryList= new Stack<>();
    private Team currentPlayer = Team.WHITE;


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

    /**
     * a method to calculate the current score of the board.
     * @return the current score. positive if white has more pieces, negative if black has more pieces.
     */
    public int getScore(){
        int whiteScore = 0;
        int blackScore = 0;

        for (Piece piece : whitePieces){whiteScore += piece.value;}
        for (Piece piece : blackPieces){blackScore += piece.value;}

        return whiteScore - blackScore;
    }

    //TODO add to interface
    public Team getTeam(){
        return currentPlayer;
    }

    /**
     * used to change the current player to the next team.
     */
    private void nextTeam(){
        switch(currentPlayer) {
            case WHITE -> currentPlayer = Team.BLACK;
            case BLACK -> currentPlayer = Team.WHITE;
        }
    }

    @Override
    public void doMove(Move move) {
        if (getSquare(move.from).isEmpty()){
            throw new RuntimeException("tried to do a move from a empty square");
        }

        if (getPiece(move.from).team != Team.WHITE && getTeam() == Team.WHITE || getPiece(move.from).team != Team.BLACK && getTeam() == Team.BLACK){
            throw new RuntimeException("tried to move a piece from the opposite team");
        }

        if (MoveHistory.numberOfMoves % 2 == 0 && currentPlayer != Team.WHITE || MoveHistory.numberOfMoves % 2 != 0 && currentPlayer != Team.BLACK){
            throw new RuntimeException("The number of moves does not match up with the current player");
            //TODO this breaks some test, fix it later.
        }
        moveHistoryList.add(new MoveHistory(this, move));

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
        nextTeam();
    }

    // TODO make interface for upgrading
    public void upgradePawn(Type type){
        int id = moveHistoryList.peek().move.to;
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
     * method to undo the moves.
     * @param numMoves number of moves back in time to undo (default should be 1)
     */
    public void undoMove(int numMoves){
        if (numMoves < 1){ throw new RuntimeException("tried to undo less then 1 move");}
        if (moveHistoryList.size() < numMoves){ return;}
        MoveHistory lastMove = null;
        for (int i = 0; i < numMoves; i++) {
            lastMove = moveHistoryList.pop();
        }
        if (lastMove == null) { throw new RuntimeException("lastMove was not initialized");}

        // Clearing the board of all pieces
        for (Piece piece : whitePieces){ getSquare(piece.getPosition()).removePiece();}
        for (Piece piece : blackPieces){ getSquare(piece.getPosition()).removePiece();}

        // Setting the new pieces to the board
        for (Piece piece : lastMove.whitePieces){ getSquare(piece.getPosition()).setPiece(piece);}
        for (Piece piece : lastMove.blackPieces){ getSquare(piece.getPosition()).setPiece(piece);}

        this.whitePieces = lastMove.whitePieces;
        this.blackPieces = lastMove.blackPieces;
        this.deadPieces = lastMove.deadPieces;
        this.currentPlayer = lastMove.currentPlayer;

        MoveHistory.numberOfMoves-= numMoves;
    }

    /**
     * handles the removal of a captured piece
     * @param Id id of the square with the captured piece
     */
    private void kill(int Id){
        Piece deathRowPiece = getSquare(Id).getPiece();
        deadPieces.add(deathRowPiece);
        List<Piece> teamList = (Team.WHITE == deathRowPiece.team ? whitePieces : blackPieces);
        teamList.remove(deathRowPiece);
        getSquare(Id).removePiece();
    }

    /**
     * checks if a square is occupied by a friendly piece.
     * @param squareId Id of the square with the piece to be checked.
     * @return returns true if piece is friendly.
     */
    public boolean isSquareFriendly(int squareId){
        if (getSquare(squareId).isEmpty()){return false;}
        if (getTeam() == Team.WHITE && getPiece(squareId).team == Team.WHITE){return true;}
        else if (getTeam() == Team.BLACK && getPiece(squareId).team == Team.BLACK){return true;}
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
        if (line.size() == 0){return new ArrayList<>();}

        int targetIndex = line.indexOf(target);
        for (int i = 0; i < targetIndex; i++) {
            squares.add(this.getSquare(line.get(i)));
        }
        return squares;
    }
}