import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */


public class Board implements IBoard{

    private final Square[] squares = new Square[64];
    private List<Piece> whitePieces = new ArrayList<>();
    private List<Piece> blackPieces = new ArrayList<>();

    public Board(){
        createBoard();
        initBoard();
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
            squares[i + 48].setPiece(blackPieces.get(i));
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
        squares[to].setPiece(movingPiece);
        squares[from].removePiece();

    }
    private void kill(int Id){
        // TODO do something with points here
        Piece deathRowPiece = squares[Id].getPiece();
        List<Piece> teamList = (Team.WHITE == deathRowPiece.team ? whitePieces : blackPieces);
        teamList.remove(deathRowPiece);
        squares[Id].removePiece();
    }

}
