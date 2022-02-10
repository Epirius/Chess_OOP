/**
 * @author Felix Kaasa
 */

public class Move {
    final int from;
    final int to;

    public Move(int from, int to){
        this.from = from;
        this.to = to;
    }

    public Move(int[] from, int[] to){
        this.from = IBoard.coordinatesToSquare(from);
        this.to = IBoard.coordinatesToSquare(to);
    }

    public int[] getMove(){
        return new int[]{from, to};
    }

}
