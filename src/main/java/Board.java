import java.util.*;

/**
 * @author Felix Kaasa
 */


public class Board implements IBoard{

    private final Square[] squares = new Square[64];
    private List<Piece> whitePieces = new ArrayList<>();
    private List<Piece> blackPieces = new ArrayList<>();
    public Stack<Move> moveHistory = new Stack<>();
    private boolean currentPlayerIsWhite = true;


    public Board(){
        createBoard();
        initBoard();
    }

    // creating a board without pieces, used for testing.
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
            squares[i].setPiece(whitePieces.get(i));
            whitePieces.get(i).setPosition(i);
            squares[i + 48].setPiece(blackPieces.get(i));
            blackPieces.get(i).setPosition(i + 48);
        }
    }

    @Override
    public Square getSquare(int Id){
        return squares[Id];
    }

    @Override
    public Piece getPiece(int squareId) {
        return squares[squareId].getPiece();
    }

    @Override
    public void doMove(Move move) {
        int from = move.getMove()[0];
        int to = move.getMove()[1];
        Piece movingPiece = squares[from].getPiece();

        if (!squares[to].isEmpty()){
            kill(to);
        }
        if (move.enPassant){kill(move.enPassantPosition);}
        squares[to].setPiece(movingPiece);
        squares[from].removePiece();
        movingPiece.setPosition(to);
        moveHistory.add(move);
        currentPlayerIsWhite = !currentPlayerIsWhite;
    }

    private void kill(int Id){
        // TODO do something with points here
        Piece deathRowPiece = squares[Id].getPiece();
        List<Piece> teamList = (Team.WHITE == deathRowPiece.team ? whitePieces : blackPieces);
        teamList.remove(deathRowPiece);
        squares[Id].removePiece();
    }

    private List<Move> getPossibleMoves(){
        List<Piece> teamList = (currentPlayerIsWhite ? whitePieces : blackPieces);
        List<Move> allPossibleMoves = new ArrayList<>();
        for (Piece piece : teamList){
            allPossibleMoves.addAll(piece.getPossibleMoves(piece.getPosition(), this));
        }
        return allPossibleMoves;
    }

    private List<Move> getPossibleThreats(){
        List<Piece> enemyList = (currentPlayerIsWhite ? blackPieces : whitePieces);
        List<Move> allThreatMoves = new ArrayList<>();
        for (Piece piece : enemyList){
            allThreatMoves.addAll(piece.getPossibleMoves(piece.getPosition(), this));
        }
        return allThreatMoves;
    }

    /**
     * finds all legal move for the current player.
     * @return List<Move>
     */
    public List<Move> getLegalMoves(){
        List<Move> moves = getPossibleMoves();
        List<Move> threats = getPossibleThreats();
        List<Integer> threatSquares = new ArrayList<>();
        for (Move move : threats){ threatSquares.add(move.to);}
        List<Move> illegalMoves = new ArrayList<>();
        List<Move> kingInCheck = new ArrayList<>();
        int king = (currentPlayerIsWhite ? getKing(Team.WHITE).getPosition() : getKing(Team.BLACK).getPosition());

        // checking if king is attacked.
        for (Move threat : threats){
            if (threat.to == king){
                kingInCheck.add(threat);
            }
        }

        for (Move move : moves){
            // checking if the king is moving into an attacked square.
            if (move.from == king && threatSquares.contains(move.to)){
                illegalMoves.add(move);
            }

            // checking if king is attacked by a piece, and the move does not kill that piece.
            if (kingInCheck.size() == 1 && move.to != kingInCheck.get(0).from && move.to != king){
                // if the attacking piece is not a queen rook or bishop:
                Type attacker = this.getSquare(kingInCheck.get(0).from).getPiece().type;
                if (!(attacker == Type.QUEEN || attacker == Type.ROOK || attacker == Type.BISHOP)){
                    // then we can't block.
                    illegalMoves.add(move);
                } else if (!squaresBetween(king, kingInCheck.get(0).from, this).contains(this.getSquare(move.to))){
                    // if you can block but didn't:
                    illegalMoves.add(move);
                }
            }
            // checking for double check.
            if (kingInCheck.size() > 1){
                if (move.from != king){
                    illegalMoves.add(move);
                }
            }
        }

        // TODO check for pinned pieces and add their moves to illegalMoves.

        // checking if castling is illegal
        King kingPiece = (King) this.getKing();
        // WHITE
        if (king == 4 ) {
            // King side
            if (threatSquares.contains(4) || threatSquares.contains(5) || threatSquares.contains(6) ||
                    !kingPiece.castleKingSide || this.getSquare(5).getPiece() != null ||
                    this.getSquare(6).getPiece() != null) {
                illegalMoves.add(new Move(4, 6, true));
            }
            // Queen side
            if (threatSquares.contains(4) || threatSquares.contains(3) || threatSquares.contains(2) ||
                    !kingPiece.castleQueenSide || this.getSquare(2).getPiece() != null ||
                    this.getSquare(3).getPiece() != null) {
                illegalMoves.add(new Move(4, 2, true));
            }
        }

        // BLACK
        if (king == 60 ) {
            // King side
            if (threatSquares.contains(60) || threatSquares.contains(61) || threatSquares.contains(62) ||
                    !kingPiece.castleKingSide || this.getSquare(61).getPiece() != null ||
                    this.getSquare(62).getPiece() != null) {
                illegalMoves.add(new Move(60, 62, true));
            }
            // Queen side
            if (threatSquares.contains(4) || threatSquares.contains(3) || threatSquares.contains(2) ||
                    !kingPiece.castleQueenSide || this.getSquare(58).getPiece() != null ||
                    this.getSquare(59).getPiece() != null) {
                illegalMoves.add(new Move(60, 58, true));
            }
        }
        moves.removeAll(illegalMoves);
        return moves;
    }

    // helper function only used in getLegalMoves()
    private Piece getKing(){
        List<Piece> teamList = (currentPlayerIsWhite ? whitePieces : blackPieces);
        Piece king = null;
        for (Piece piece : teamList){
            if (piece.type == Type.KING){king = piece;}
        }
        return king;
    }

    // helper function only used in getLegalMoves()
    private Piece getKing(Team team){
        List<Piece> teamList = (team == Team.WHITE ? whitePieces : blackPieces);
        Piece king = null;
        for (Piece piece : teamList){
            if (piece.type == Type.KING){king = piece;}
        }
        return king;
    }


    private List<Square> squaresBetween(){
        //TODO
    }
}





































