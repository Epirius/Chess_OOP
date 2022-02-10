/**
 * @author Felix Kaasa
 */

public class Bishop extends Piece{

    public Bishop(Team team) {
        super(team, Type.BISHOP);
    }

    @Override
    public Move[] getPossibleMoves() {
        return new Move[0];
    }
}
