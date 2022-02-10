/**
 * @author Felix Kaasa
 */

public class Pawn extends Piece{


    public Pawn(Team team) {
        super(team, Type.PAWN);
    }

    @Override
    public Move[] getPossibleMoves() {
        return new Move[0];
    }
}
