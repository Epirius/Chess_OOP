import java.util.concurrent.ExecutionException;

public class Board implements IBoard{

    private Square[] squares = new Square[64];

    public Board(){
        createBoard();
        //initBoard();
    }

    @Override
    public void createBoard() {
        for (int i = 0; i < 64; i++) {
            squares[i] = new Square(i);
        }
    }

    @Override
    public void initBoard() {

    }

    @Override
    public Square getSquare(int Id){
        return squares[Id];
    }

    @Override
    public Piece getPiece(int squareId) {
        return squares[squareId].getPiece();
    }

}
