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

    @Override
    public int[] squareToCoordinates(int Id) throws Exception {
        int x;
        int y;

        if (Id < 0 || Id > 63) {throw new Exception("ID is out of bounds!");}
        if (0 <= Id && Id < 8){
            x = Id;
            y = 1;
            return  new int[]{x, y};
        }
        for (int i = 2; i < 8; i++) {
            int calculation = Id - (8 * i);
            if (calculation >= 0 && calculation < 8){
                x = calculation;
                y = i;
                return  new int[]{x, y};
            }
        }
        throw new Exception("something went wrong with converting square id to coordinates!");
    }

    @Override
    public int coordinatesToSquare(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        int id = x + (y * 8);
        return id;
    }
}
