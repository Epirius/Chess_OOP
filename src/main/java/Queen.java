/**
 * @author Felix Kaasa
 */

public class Queen extends Piece{

    public Queen(Team team) {
        super(team, Type.QUEEN);
    }

    @Override
    public Move[] getPossibleMoves() {
        return new Move[0];
    }
}
