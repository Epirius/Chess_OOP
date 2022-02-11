/**
 * @author Felix Kaasa
 */

public class Move {
    public final int from;
    public final int to;
    private boolean castle = false;

    public Move(int from, int to){
        this.from = from;
        this.to = to;
    }

    public Move(int[] from, int[] to){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
    }



    public Move(int from, int to, boolean castle){
        this.from = from;
        this.to = to;
        this.castle = castle;
    }

    public Move(int[] from, int[] to, boolean castle){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
        this.castle = castle;
    }

    public int[] getMove(){
        return new int[]{from, to};
    }

}
